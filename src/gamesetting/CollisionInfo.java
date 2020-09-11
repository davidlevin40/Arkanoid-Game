package gamesetting;
// ID: 316554641

import geometry.Point;
import interfaces.Collidable;

/**
 * CollisionInfo class.
 * A CollisionInfo representing the information of a collision object (in the game).
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Construct a CollisionInfo object - information of an object (collide object).
     * @param x the direction of movement of a given ball
     * @param c a Collidable object
     */
    public CollisionInfo(Point x, Collidable c) {
        this.collisionPoint = x;
        this.collisionObject = c;
    }

    /**
     * @return the collision point of the object with different object (the point at which the collision occurs).
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
