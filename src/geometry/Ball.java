package geometry;
// 316554641

import gamesetting.CollisionInfo;
import gamesetting.GameLevel;
import gamesetting.GameEnvironment;
import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;

/**
 * Ball class.
 * The class will create ball, define the balls movements, draw the balls and define the color, size etc.
 */
public class Ball implements Sprite {
    // Geometry.Point that represents the center point of a ball
    private Point center;

    // radius of a ball
    private int r;

    // color of a ball
    private Color color;

    // velocity of an object
    private Velocity velocity;

    // game environment
    private GameEnvironment envi;

    /**
     * Construct a ball- given center point, radius and color.
     *
     * @param center the center point of the ball
     * @param r the radius of the circle
     * @param color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.envi = null;
    }

    /**
     * Construct a line by given start and end points.
     *
     * @param x the x value of the center point
     * @param y the y value of the center point
     * @param r the radius of the circle
     * @param color of the ball
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.color = color;
        this.r = r;
        this.envi = null;
    }

    /**
     * @return the x coordinate of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y coordinate of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the coordinate of the center point
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return the radius (of a ball)
     */
    public int getSize() {
        return (int) this.r;
    }

    /**
     * @return the color (of a ball)
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * The function role is to draw the ball on the given DrawSurface.
     * <p>
     * @param surface that will be the draw surface of our program
     */
    public void drawOn(DrawSurface surface) {
        //creating the surface
        DrawSurface d = surface;

        int x = this.getX();
        int y = this.getY();
        int radius = this.r;
        d.setColor(this.color);
        //define the ball
        d.fillCircle(x, y, radius);
        d.setColor(Color.BLACK);
        d.drawCircle(x, y, radius);
    }

    /**
     * Set the velocity of an object.
     *
     * @param v that represent the velocity of something (in this case ball).
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set the velocity of an object.
     *
     * @param dx that represent the possible change in 'x' value
     * @param dy that represent the possible change in 'y' value
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The function will determine where is the "next step" of the ball
     * <p>
     * The function will calculate where should be the next location of a given ball. It will consider the given
     * draw surface- by define the bounds of the ball movement (the calculation consider the radius of the ball -
     * we want that all the area off the ball will eb inside).
     * <p>
     * @param surface that represent the new draw surface
     */
    public void moveOneStep(DrawSurface surface) {
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        int radius = this.r;

        //define the bounds movement of the ball (considering the radius)
        double minBallX = 0 + radius;
        double minBallY = 0 + radius;
        double maxBallX = surface.getWidth() - radius;
        double maxBallY = surface.getHeight() - radius;

        //define the left bound ot the draw surface
        if (x < minBallX) {
            this.setVelocity(Math.abs(dx), dy);
        }
        //define the right bound ot the draw surface
        if (x > maxBallX) {
            this.setVelocity(-Math.abs(dx), dy);
        }
        //define the 'up' bound ot the draw surface
        if (y < minBallY) {
            this.setVelocity(dx, Math.abs(dy));
        }
        //define the 'down' bound ot the draw surface
        if (y > maxBallY) {
            this.setVelocity(dx, -Math.abs(dy));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * The function will determine where is the "next step" of the ball (but with different arguments)
     * <p>
     * The function will calculate where should be the next location of a given ball. It will consider the given
     * draw surface- by define the bounds of the ball movement (the calculation consider the radius of the ball -
     * we want that all the area off the ball will eb inside).
     * <p>
     * @param surface that represent the new draw surface
     * @param xMin that represent the min value that 'x' can be
     * @param yMin that represent the min value that 'y' can be
     * @param xMax that represent the max value that 'x' can be
     * @param yMax that represent the max value that 'y' can be
     */
    public void moveOneStep(DrawSurface surface, int xMin, int yMin, int xMax, int yMax) {
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        int radius = this.r;

        //define the bounds movement of the ball (considering the radius)
        double minBallX = xMin + radius;
        double minBallY = yMin + radius;
        double maxBallX = xMax - radius;
        double maxBallY = yMax - radius;

        //define the left bound ot the draw surface
        if (x < minBallX) {
            this.setVelocity(Math.abs(dx), dy);
        }
        //define the right bound ot the draw surface
        if (x > maxBallX) {
            this.setVelocity(-Math.abs(dx), dy);
        }
        //define the 'up' bound ot the draw surface
        if (y < minBallY) {
            this.setVelocity(dx, Math.abs(dy));
        }
        //define the 'down' bound ot the draw surface
        if (y > maxBallY) {
            this.setVelocity(dx, -Math.abs(dy));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * The function will determine where is the "next step" of the ball
     * <p>
     * The function will calculate where should be the next location of a given ball. It will consider the given
     * draw surface- by define the bounds of the ball movement (the calculation consider the radius of the ball -
     * we want that all the area off the ball will eb inside).
     */
    public void moveOneStep() {
        // get the values of the start point (of the line)
        double x1 = center.getX();
        double y1 = center.getY();
        // get the values of the end point (of the line)
        double x2 = center.getX() + velocity.getDx();
        double y2 = center.getY() + velocity.getDy();
        // creating the line
        Line line = new Line(x1, y1, x2, y2);
        CollisionInfo check = this.envi.getClosestCollision(line);
        // check if there is an intersection point
        if (check == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        if (check != null) {
            Point move = check.collisionPoint();
            Collidable strike = check.collisionObject();
            // if there is an intersection point- update the velocity considering the hit
            this.velocity = strike.hit(this, move, this.velocity);
        }
    }

    /**
     * Set the game environment of an object.
     *
     * @param gameEnvironment that represent the game environment for the game.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.envi = gameEnvironment;
    }

    /**
     * @return the gameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.envi;
    }

    /**
     * notify that the time passed and need to move one step (the ball).
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add ball to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * The function role is to remove the ball from the game.
     * @param gameLevel that represents the game that we want to update.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}