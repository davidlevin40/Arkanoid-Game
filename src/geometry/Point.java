package geometry;

/**
 * Geometry.Point class.
 * A Geometry.Point representing a location in (x,y) coordinate space, specified in integer precision.
 */
public class Point {
    //x coordinate value
    private double x;

    //y coordinate value
    private double y;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other a point to measure the distance to another point
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double dx = x - other.getX();
        double dy = y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * The function will return true if the points are equal, false otherwise.
     * <p>
     * @param other that represents point that we want to check
     * @return the function return value is boolean - true or false. */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return ((x == other.x) && (y == other.y));
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

}
