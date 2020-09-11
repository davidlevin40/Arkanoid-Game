package interfaces;
// ID: 316554641

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * Collidable interface.
 * A Collidable interface representing the information of a collidable object (in the game).
 */
public interface Collidable {

    /**
     * @return the "collision rectangle" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     return the velocity of the ball after the collision with some block.
     *
     * @param collisionPoint that represents the "collision location" of a given point.
     * @param currentVelocity that represents the current velocity of an object.
     * @param hitter that represents the ball.
     * @return the function return value is Velocity - the new velocity after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}