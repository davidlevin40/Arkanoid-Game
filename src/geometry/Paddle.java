package geometry;
// ID: 316554641

import biuoop.DrawSurface;
import java.awt.Color;
import biuoop.KeyboardSensor;
import gamesetting.GameLevel;
import interfaces.Collidable;
import interfaces.Sprite;

/**
 * Paddle class.
 * A Paddle representing an object, that will use us to play the game.
 */
public class Paddle implements Sprite, Collidable {
    // width of 'screen'
    private static final int WIDTH = 800;
    // change of movement for the paddle
    private static final int SPEED_BALL = 5;
    // change of movement for the paddle
    private static final int SPEED_PADDLE = 7;
    // height of rectangle
    private static final int REC_SIZE_HEIGHT = 15;

    private KeyboardSensor keyboard;
    private Color color;
    private Rectangle rectangle;
    private int speed;

    /**
     * Construct a Paddle with color, keyboardSensor and rectangle.
     *
     * @param color the color of the block.
     * @param rectangle the form of the block.
     * @param keyboardSensor the sensor of the paddle.
     * @param speed that represents the speed of the paddle.
     */
    public Paddle(Color color, Rectangle rectangle, KeyboardSensor keyboardSensor, int speed) {
        this.color = color;
        this.rectangle = rectangle;
        this.keyboard = keyboardSensor;
        this.speed = speed;
    }

    /**
     * The function role is to move left the paddle (by creating new rectangle for the paddle).
     *
     */
    public void moveLeft() {
        // change only if the paddle is inside the game boards
        if (rectangle.getUpperLeft().getX() >= REC_SIZE_HEIGHT + SPEED_PADDLE + 5) {
            Point temp = this.rectangle.getUpperLeft();
            // change the x value to the imagine change of speed
            double x = temp.getX() - this.speed;
            double y = temp.getY();
            Point update = new Point(x, y);
            // create the updated rectangle
            this.rectangle = new Rectangle(update, this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * The function role is to move right the paddle (by creating new rectangle for the paddle).
     *
     */
    public void moveRight() {
        // change only if the paddle is inside the game boards
        if (rectangle.getUpperRight().getX() <= WIDTH - (REC_SIZE_HEIGHT + SPEED_PADDLE + 5)) {
            Point temp = this.rectangle.getUpperLeft();
            // change the x value to the imagine change of speed
            double x = temp.getX() +  this.speed;
            double y = temp.getY();
            Point update = new Point(x, y);
            // create the updated rectangle
            this.rectangle = new Rectangle(update, this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * notify that the time passed and need to move one step (the ball).
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * The function role is to draw the block on the given DrawSurface.
     * <p>
     * @param d that will be the draw surface of our program
     */
    public void drawOn(DrawSurface d) {
        //creating the surface
        DrawSurface surface = d;

        //setting the values for the rectangle
        double x = this.rectangle.getUpperLeft().getX();
        double y = this.rectangle.getUpperLeft().getY();
        double height = this.rectangle.getHeight();
        double width = this.rectangle.getWidth();
        //define the rectangle
        d.setColor(this.color);
        d.fillRectangle((int) x, (int) y, (int) width, (int) height);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * @return the Geometry.Rectangle collision
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * The function will return the velocity of the ball after the collision with some block.
     * <p>
     * The function will take a given point in position (x,y) and will calculate new point with a new position:
     * referring to the point of impact with the rectangle. Also, it will take care for some special cases: when the
     * ball hits exactly in the vertex of the rectangle
     * <p>
     * @param collisionPoint that represents the "collision location" of a given point.
     * @param currentVelocity that represents the current velocity of an object.
     * @param hitter that represents the ball.
     * @return the function return value is Velocity - the new velocity after the collision */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point helper = collisionPoint;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // upper left point of the rectangle (values)
        double x1 = this.rectangle.getUpperLeft().getX();
        double y1 = this.rectangle.getUpperLeft().getY();
        // upper right point of the rectangle (values)
        double x2 = this.rectangle.getUpperRight().getX();
        double y2 = this.rectangle.getUpperRight().getY();
        // down left point of the rectangle (values)
        double x3 = this.rectangle.getDownLeft().getX();
        double y3 = this.rectangle.getDownLeft().getY();
        // down right point of the rectangle (values)
        double x4 = this.rectangle.getDownRight().getX();
        double y4 = this.rectangle.getDownRight().getY();
        // upper left point of the rectangle
        Point rec = this.rectangle.getUpperLeft();
        // length of the paddle
        double lengthPaddle = this.rectangle.getWidth();
        double sizePad = lengthPaddle / 5;


        // if the hit point is in the left side of the rectangle: area 1 (left)
        if ((rec.getX() <= helper.getX()) && (helper.getX() <= rec.getX() + sizePad) && (helper.getY() == rec.getY())) {
            currentVelocity = Velocity.fromAngleAndSpeed(-60, SPEED_BALL);
        }
        // if the hit point is in the left side of the rectangle: area 2
        if ((rec.getX() + sizePad <= helper.getX()) && (helper.getX() <= rec.getX() + 2 * sizePad)
                && (helper.getY() == rec.getY())) {
            currentVelocity = Velocity.fromAngleAndSpeed(-30, SPEED_BALL);
        }
        // if the hit point is in the left side of the rectangle: area 3 (middle)
        if ((rec.getX() + 2 * sizePad <= helper.getX()) && (helper.getX() <= rec.getX() + 3 * sizePad)
                && (helper.getY() == rec.getY())) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        // if the hit point is in the left side of the rectangle: area 4
        if ((rec.getX() + 3 * sizePad <= helper.getX()) && (helper.getX() <= rec.getX() + 4 * sizePad)
                && (helper.getY() == rec.getY())) {
            currentVelocity = Velocity.fromAngleAndSpeed(30, SPEED_BALL);
        }
        // if the hit point is in the left side of the rectangle: area 5 (right)
        if ((rec.getX() + 4 * sizePad <= helper.getX()) && (helper.getX() <= rec.getX() + 5 * sizePad)
                && (helper.getY() == rec.getY())) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, SPEED_BALL);
        }
        // if the hit point is in the left side of the rectangle
        if ((helper.getX() == x1) && (helper.getX() == x3) && (helper.getY() >= y1) && (helper.getY() <= y3)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        // if the hit point is in the right side of the rectangle
        if ((helper.getX() == x2) && (helper.getX() == x4) && (helper.getY() >= y2) && (helper.getY() <= y4)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
