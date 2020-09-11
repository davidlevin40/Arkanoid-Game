package geometry;

import biuoop.DrawSurface;
import gamesetting.GameLevel;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class.
 * A Block representing an object, that will use us in creating the game environment.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Construct a block with color and rectangle.
     *
     * @param color the color of the block
     * @param r the form of the block
     */
    public Block(Rectangle r, Color color) {
        this.rectangle = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
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
        // upper left point of the rectangle
        double x1 = this.rectangle.getUpperLeft().getX();
        double y1 = this.rectangle.getUpperLeft().getY();
        // upper right point of the rectangle
        double x2 = this.rectangle.getUpperRight().getX();
        double y2 = this.rectangle.getUpperRight().getY();
        // down left point of the rectangle
        double x3 = this.rectangle.getDownLeft().getX();
        double y3 = this.rectangle.getDownLeft().getY();
        // down right point of the rectangle
        double x4 = this.rectangle.getDownRight().getX();
        double y4 = this.rectangle.getDownRight().getY();

        // if the hit point is in the left side of the rectangle
        if ((helper.getX() == x1) && (helper.getX() == x3) && (helper.getY() >= y1) && (helper.getY() <= y3)) {
            currentVelocity.setDx(-currentVelocity.getDx());
            this.notifyHit(hitter);
        }
        // if the hit point is in the right side of the rectangle
        if ((helper.getX() == x2) && (helper.getX() == x4) && (helper.getY() >= y2) && (helper.getY() <= y4)) {
            currentVelocity.setDx(-currentVelocity.getDx());
            this.notifyHit(hitter);
        }
        // if the hit point is in the upper side of the rectangle
        if ((helper.getY() == y1) && (helper.getY() == y2) && (helper.getX() >= x1) && (helper.getX() <= x2)) {
            currentVelocity.setDy(-currentVelocity.getDy());
            this.notifyHit(hitter);
        }
        // if the hit point is in the down side of the rectangle
        if ((helper.getY() == y3) && (helper.getY() == y4) && (helper.getX() >= x3) && (helper.getX() <= x4)) {
            currentVelocity.setDy(-currentVelocity.getDy());
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    /**
     * The function role is to draw the block on the given DrawSurface.
     * <p>
     * @param surface that will be the draw surface of our program
     */
    public void drawOn(DrawSurface surface) {
        //creating the surface
        DrawSurface d = surface;

        // setting the values for the rectangle
        double x = this.rectangle.getUpperLeft().getX();
        double y = this.rectangle.getUpperLeft().getY();
        double height = this.rectangle.getHeight();
        double width = this.rectangle.getWidth();
        // define the rectangle
        d.setColor(this.color);
        d.fillRectangle((int) x, (int) y, (int) width, (int) height);
        d.setColor(Color.black);
        d.drawRectangle((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * Add objects to the game - new blocks.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * notify the block that time has passed.
     */
    public void timePassed() {

    }

    /**
     * The function role is to notify the listeners when a hit is happening.
     * @param hitter that represent the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            // adding objects to the list - only if this is the first rime.
            if (!listeners.contains(hl)) {
                addHitListener(hl);
            }
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * The function role is to add listener to hit events in the game.
     * @param hl Add hl as a listener to hit events.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * The function role is to remove listener to hit events in the game.
     * @param hl Remove hl from the list of listeners to hit events.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Remove this object from the game - the block.
     * @param gameLevel that represents the game that we want to update.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
}
