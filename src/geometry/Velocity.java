package geometry;
// ID: 316554641


/**
 * Velocity class.
 * The class will define the velocity of an object in some cases (from angled, speed, dx, dy).
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Construct velocity value - considering 'x' and 'y' values.
     *
     * @param dx the center point of the ball
     * @param dy the radius of the circle
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The function will take a given point and return new point.
     * <p>
     * The function will take a given point in position (x,y) and return a new point with a new position: (dx+x, dy+y)
     * <p>
     * @param p that represents the "start location" of a given point.
     * @return the function return value is Geometry.Point - the point with the new location (temp) */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        Point temp = new Point(x, y);
        return temp;
    }

    /**
     * @return the dx coordinate
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy coordinate
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Set the velocity of an object.
     *
     * @param dx1 that represent the possible change in 'y' value
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }

    /**
     * Set the velocity of an object.
     *
     * @param dy1 that represent the possible change in 'y' value
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }

    /**
     * The function will calculate the 'exchange' of speed and angle- and will return the velocity.
     * <p>
     * The function will calculate the velocity, by looking at the Cartesian coordinate: the possible movement of
     * any object in 2-d dimensional. The calculations will based on the trigonometry rules:
     * dx will be positive while move right, dy will be negative while move left,
     * dy will ve positive while move up, dy will be negative while move down.
     * <p>
     * @param angle that represents the point of contact
     * @param speed that represents the speed of movement
     * @return the function return value is Velocity - the velocity of a given "situation" */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert (speed, angle) to (x, y), with y-axis inverted
        double dx = 0;
        double dy = 0;

        //the movement is vertically, so we will change only 'y'
        if (angle == 0) {
            dx = 0;
            dy = -speed;
        }

        //the movement is horizontally, so we will change only 'x'
        if (angle == 90) {
            dx = speed;
            dy = 0;
        }

        //the movement is vertically, so we will change only 'y'
        if (angle == 180) {
            dx = 0;
            dy = speed;
        }

        //the movement is horizontally, so we will change only 'x'
        if (angle == 270) {
            dx = -speed;
            dy = 0;
        }
        //any other case that not include the special cases
        if ((angle != 0) && (angle != 90) && (angle != 180) && (angle != 270)) {
            dx = speed * Math.sin(Math.toRadians(angle));
            dy = -speed * Math.cos(Math.toRadians(angle));
        }
        return new Velocity(dx, dy);
    }
}

