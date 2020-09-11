package geometry;
// ID: 316554641


import java.util.ArrayList;
import java.util.List;

/**
 * Geometry.Rectangle class.
 * A rectangle representing an object, that will use us in creating the game.
 */
public class Rectangle {

    // rectangle width
    private double width;
    // rectangle height
    private double height;
    // upper left point of the rectangle
    private Point upperLeft;

    /**
     * Construct a rectangle  with location, width and height.
     *
     * @param width the width of the rectangle
     * @param height the width of the rectangle
     * @param upperLeft the upper left point of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }

    /**
     * The function will create a list (maybe empty) of the intersection points of a given line with a rectangle.
     * <p>
     * The function will use another functions, that will help her to decide if the given line has any intersection
     * points. If there is some- it will arrange it in a new list of points.
     * <p>
     * @param line that represents the line that we want to check (like described above)
     * @return the function return value is List of potential intersection points (java.util.List<Geometry.Point>). */
    public java.util.List<Point> intersectionPoints(Line line) {

        List<Point> interPointList = new ArrayList<>();
        // find if there is an intersection points between the upper line and the given line
        if (getUpLine().isIntersecting(line)) {
            interPointList.add(getUpLine().intersectionWith(line));
        }
        // find if there is an intersection points between the down line and the given line
        if (getDownLine().isIntersecting(line)) {
            interPointList.add(getDownLine().intersectionWith(line));
        }
        // find if there is an intersection points between the right line and the given line
        if (getRightLine().isIntersecting(line)) {
            interPointList.add(getRightLine().intersectionWith(line));
        }
        // find if there is an intersection points between the left line and the given line
        if (getLeftLine().isIntersecting(line)) {
            interPointList.add(getLeftLine().intersectionWith(line));
        }
        return interPointList;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the  the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return the  the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        Point x = new Point(upperLeft.getX() + width, upperLeft.getY());
        return x;
    }

    /**
     * @return the  the down-right point of the rectangle.
     */
    public Point getDownRight() {
        Point x = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        return x;
    }

    /**
     * @return the  the down-left point of the rectangle.
     */
    public Point getDownLeft() {
        Point x = new Point(upperLeft.getX(), upperLeft.getY() + height);
        return x;
    }

    /**
     * @return the  the upper line of the rectangle.
     */
    public Line getUpLine() {
        Line a = new Line(getUpperLeft(), getUpperRight());
        return a;
    }

    /**
     * @return the  the down line of the rectangle.
     */
    public Line getDownLine() {
        Line a = new Line(getDownLeft(), getDownRight());
        return a;
    }

    /**
     * @return the  the right line of the rectangle.
     */
    public Line getRightLine() {
        Line a = new Line(getUpperRight(), getDownRight());
        return a;
    }

    /**
     * @return the  the left line of the rectangle.
     */
    public Line getLeftLine() {
        Line a = new Line(getUpperLeft(), getDownLeft());
        return a;
    }
}