package gamesetting;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class.
 * A GameEnvironment representing the collection of objects that will appear in the screen while playing the game.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Construct a GameEnvironment object - list of collidables.
     *
     */
    public GameEnvironment() {
        collidables = new ArrayList<>();
    }

    /**
     * The function role is to add a new object to the collidables list--> to the environment.
     *
     * @param c a Collidable object to add to the list
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * The function will get a movement line of an object (line), and will find out if there is collide with any of
     * the collidables in the collection.
     * <p>
     * The function will check every collide object in the list, to find out if there is any intersection points.
     * If there is not any intersection point- it will return null point and null object. Either, the function
     * will find the closest collision that is going to occur: and will return it and the collide object.
     * <p>
     * @param trajectory that represents the line that we want to check.
     * @return the function return the information about the closest collision that is going to occur */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // define the CollisionInfo values
        Point closestInterPoint = null;
        Collidable helper = null;

        // traverse any object in the list
        for (Collidable c : this.collidables) {
            Rectangle r = c.getCollisionRectangle();
            // check if there is any intersection points between the object and the line
            if (!r.intersectionPoints(trajectory).isEmpty()) {
                // "taking" the closest value of the intersection point to a new point.
                Point temp = trajectory.closestIntersectionToStartOfLine(r);
                // if this is the first potential intersection point- redefine the values of the CollisionInfo
                if (closestInterPoint == null) {
                    closestInterPoint = temp;
                    helper = c;
                }
                // calculating the distance between the point to the line (for both- temp, closestInterPoint)
                double dis1 = trajectory.start().distance(temp);
                double dis2 = trajectory.start().distance(closestInterPoint);
                // find between the two points, who is the closer to the line: update the point and the object.
                if (dis1 < dis2) {
                    closestInterPoint = temp;
                    helper = c;
                }
            }
        }
        if (closestInterPoint != null) {
            CollisionInfo info = new CollisionInfo(closestInterPoint, helper);
            return info;
        }
        return null;
    }

    /**
     * The function role is to remove a collidable from the list.
     * @param c that represents the collidable that we want to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
}
