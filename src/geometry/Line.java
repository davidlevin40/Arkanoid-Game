package geometry;
// ID: 316554641


import java.util.ArrayList;
import java.util.List;

/**
 * Geometry.Line class.
 * The class support many operations: identifying if there is an intersection points, length of line etc.
 * It is implemented using the Geometry.Point class (each line is connection between two points).
 */
public class Line {
    //start point of a given line
    private Point start;

    //middle point of a given line
    private Point middle;

    //end point of a given line
    private Point end;

    /**
     * Construct a line given start and end points.
     *
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Construct a line given 4 values: x coordinate (x2), y coordinate (x2).
     *
     * @param x1 the x value of the point start
     * @param y1 the y value of the point start
     * @param x2 the x value of the point end
     * @param y2 the y value of the point end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     *
     * @return the length of given line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     *
     * @return the middle point of the line
     */
    public Point middle() {
        // equation to find the middle point of a given line
        this.middle = new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
        return this.middle;
    }

    /**
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * The function will determine if 2 lines are intersecting or not.
     * <p>
     * The function will use another functions, that will help her to decide if the 2 given lines
     * that we want to check are intersecting.
     * <p>
     * @param other that represents the line that we want to check (like described above)
     * @return the function return value is boolean - true or false. */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        // if the lines are the same- return false
        if (this.equals(other)) {
            return false;
        }
        // calling another function that return 'true' or 'false' about intersection point
        if (isIntersectingHelper(this.start, this.end, other.start, other.end)) {
            return true;
        }
        return false;
    }

    /**
     * The function will find out what is the intersection point of 2 lines.
     * <p>
     * The function will "start to work" only after we will know if there is a potential intersection point (first if).
     * Every 'if' inside the function will check another possibility: verticals lines, horizon lines - and if
     * this is a 'simple' case, we will find the values of the point in the end of the function.
     * The calculation based on the equation y=mx+b.
     * <p>
     * @param other that represents the line that we want to check (like described above)
     * @return the function return value is Geometry.Point - the intersection point */
    public Point intersectionWith(Line other) {
        // will stop if there is no a potential point
        if (!this.isIntersecting(other)) {
            return null;
        }
        // 2 lines are verticals
        if ((other.start.getX() - other.end.getX() == 0) && (this.start.getX() - this.end.getX() == 0)) {
            return this.verticalLine(other, 3);
        }
        // 1 line is vertical ('this')
        if (this.start.getX() - this.end.getX() == 0) {
            return this.verticalLine(other, 1);
        }
        // 1 line is vertical ('other')
        if (other.start.getX() - other.end.getX() == 0) {
            return this.verticalLine(other, 2);
        }
        // 2 lines are horizons
        if ((this.start.getY() - this.end.getY() == 0) && (other.start.getY() - other.end.getY() == 0)) {
            return this.horizonLine(other, 6);
        }
        // 1 line is horizon ('this')
        if (this.start.getY() - this.end.getY() == 0)  {
            return this.horizonLine(other, 4);
        }
        // 1 line is horizon ('other')
        if (other.start.getY() - other.end.getY() == 0) {
            return this.horizonLine(other, 5);
        }
        // finding the slopes
        double slope1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double slope2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        //finding the 'b' value of the equation y=mx+b
        double inter1 = this.start.getY() - (slope1 * this.start.getX());
        double inter2 = other.start.getY() - (slope2 * other.start.getX());
        //finding the intersection point
        double x = (inter2 - inter1) / (slope1 - slope2);
        double y = (slope1 * x) + inter1;
        //creating the intersection point
        Point intersection = new Point(x, y);
        return intersection;
    }

    /**
     * The function will determine if 2 lines are exactly the same.
     * <p>
     * The function will check if the 2 given lines have the same values exactly (x and y values).
     * <p>
     * @param other that represents the line that we want to check (like described above)
     * @return the function return value is boolean - true or false. */
    public boolean equals(Line other) {
        //define arguments that will simplify our code
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        if ((x1 == x3) && (y1 == y3) && (x2 == x4) && (y2 == y4)) {
            return true;
        }
        return false;
    }

    /**
     * The function will determine if a given point 'lies' on a given segment.
     * <p>
     * The function will check if the 'q' point is somewhere in the given segment
     * <p>
     * @param p that represents one side of the segment (p-r)
     * @param q that represents the given point that we want to check
     * @param r that represents the other side of the segment (p-r)
     * @return the function return value is boolean - true or false. */
    public boolean checkOnLine(Point p, Point q, Point r) {
        int flag = 0;
        //checking if 'x' value of the point q is in the segment
        if ((q.getX() <= Math.max(p.getX(), r.getX())) && (q.getX() >= Math.min(p.getX(), r.getX()))) {
            flag++;
        }
        //checking if 'y' value of the point q is in the segment
        if ((q.getY() <= Math.max(p.getY(), r.getY())) && (q.getY() >= Math.min(p.getY(), r.getY()))) {
            flag++;
        }
        //the 'x' and 'y' values of q is on the segment
        if (flag == 2) {
            return true;
        }
        return false;
    }

    /**
     * The function will find the 'orientation behavior' of a 3 given points.
     * <p>
     * The function based on the orientation idea: geometric notion that in two dimensions allows one
     * to say when a cycle goes around clockwise or counterclockwise. The function will check 3 possible cases:
     * Clockwise behavior(will return 1), Counterclockwise behavior(will return 2), collinear- the 3 points are in the
     * same segment(will return 0).
     * <p>
     * @param p1 that represents one point
     * @param p2 that represents one point
     * @param p3 that represents one point
     * @return the function return value is int - like described above. */
    public int direction(Point p1, Point p2, Point p3) {
        // subtraction values to find exactly the 'x' and 'y' values for 3 the given point.
        // then multiplying the result and again subtracting.
        // with this result we can find the  'orientation behavior'.
        double helper = ((p2.getY() - p1.getY()) * (p3.getX() - p2.getX()))
                - ((p2.getX() - p1.getX()) * (p3.getY() - p2.getY()));
        // collinear behavior
        if (helper == 0) {
            return 0;
        }
        // Clockwise behavior
        if (helper > 0) {
            return 1;
        }
        // Counterclockwise behavior
        return 2;
    }

    /**
     * The function will determine if a given 4 points (2 lines), are intersecting- but will check if the point is a
     * legal intersection (just one common point).
     * <p>
     * The function will call another functions to determine if there is only one point in common.
     * First, it will use the 'orientation behavior' to find if any common point exists.
     * Then, it will call another function to find if this is only one point in common, or infinity points.
     * The "brain" of this class.
     * <p>
     * @param p1 that represents one point
     * @param q1 that represents one point
     * @param p2 that represents one point
     * @param q2 that represents one point
     * @return the function return value is boolean - true or false. */
    public boolean isIntersectingHelper(Point p1, Point q1, Point p2, Point q2) {
        // identifying the 'orientation behavior' of a 3 given points (x1, x2, x3, x4)
        int x1 = direction(p1, q1, p2);
        int x2 = direction(p1, q1, q2);
        int x3 = direction(p2, q2, p1);
        int x4 = direction(p2, q2, q1);

        //when the two lines are intersecting
        if (x1 != x2 && x3 != x4) {
            return true;
        }
        //when the point 'p2' is on segment 'p1-q1'
        if (x1 == 0 && checkOnLine(p1, p2, q1)) {
            // calling the function that determine if there is only one intersection point
            if (!wrongCases(p1, q1, p2, q2)) {
                return true;
            }
            return false;
        }
        //when the point 'q2' is on segment 'p1-q1'
        if (x2 == 0 && checkOnLine(p1, q2, q1)) {
            // calling the function that determine if there is only one intersection point
            if (!wrongCases(p1, q1, p2, q2)) {
                return true;
            }
            return false;
        }
        //when the point 'p1' is on segment 'p2-q2'
        if (x3 == 0 && checkOnLine(p2, p1, q2)) {
            // calling the function that determine if there is only one intersection point
            if (!wrongCases(p1, q1, p2, q2)) {
                return true;
            }
            return false;
        }
        //when the point 'q1' is on segment 'p2-q2'
        if (x4 == 0 && checkOnLine(p2, q1, q2)) {
            // calling the function that determine if there is only one intersection point
            if (!wrongCases(p1, q1, p2, q2)) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * The function will determine if a given 4 points (2 lines), are intersecting just in only one point.
     * <p>
     * The function will "start to working" only if we know for sure that at least one common point exists.
     * Then, the function will find out if the lines creating a situation that cause more then one point:
     * one line "eating" another line, one line is 'subset' of another, etc.
     * <p>
     * @param p1 that represents one point
     * @param q1 that represents one point
     * @param p2 that represents one point
     * @param q2 that represents one point
     * @return the function return value is boolean - true (illegal situation) or false (legal situation). */
    public boolean wrongCases(Point p1, Point q1, Point p2, Point q2) {
        // epsilon that represent a minor change - will help us to find if this is
        // only one intersection point (or infinity...)
        double epsilon = 0.0001;

        // finding the biggest and smallest values of any line (x and y values)
        double firstMaxX = Math.max(p1.getX(), q1.getX());
        double firstMinX = Math.min(p1.getX(), q1.getX());
        double firstMaxY = Math.max(p1.getY(), q1.getY());
        double firstMinY = Math.min(p1.getY(), q1.getY());
        double secondMaxX = Math.max(p2.getX(), q2.getX());
        double secondMinX = Math.min(p2.getX(), q2.getX());
        double secondMaxY = Math.max(p2.getY(), q2.getY());
        double secondMinY = Math.min(p2.getY(), q2.getY());

        // the biggest 4 values of all lines
        double xMax = Math.max(firstMaxX, secondMaxX);
        double xMin = Math.min(firstMinX, secondMinX);
        double yMax = Math.max(firstMaxY, secondMaxY);
        double yMin = Math.min(firstMinY, secondMinY);

        //one line is "eating" other line
        if ((xMax == firstMaxX) && (xMin == firstMinX) && (yMax == firstMaxY) && (yMin == firstMinY)) {
            return true;
        }
        //one line is "eating" other line
        if ((xMax == secondMaxX) && (xMin == secondMinX) && (yMax == secondMaxY) && (yMin == secondMinY)) {
            return true;
        }
        //one line is "touching" the other line only in exactly one point
        if ((p1 == p2) || (p1 == q2) || (q1 == p2) || (q1 == q2)) {
            return false;
        }
        //there are infinity common points: we will add an 'epsilon' to find out if after we add such a small value,
        //if the 2 lines will still touch each other - means that there are infinity common points.
        if ((q1.getX() - epsilon >= secondMinX && q1.getX() + epsilon <= secondMaxX)
                || (q1.getY() - epsilon >= secondMinY && q1.getY() + epsilon <= secondMaxY)
                || (p1.getX() - epsilon >= secondMinX && p1.getX() + epsilon <= secondMaxX)
                || (p1.getY() - epsilon >= secondMinY && p1.getY() + epsilon <= secondMaxY)
                || (q2.getX() - epsilon >= firstMinX && q2.getX() + epsilon <= firstMaxX)
                || (q2.getY() - epsilon >= firstMinY && q2.getY() + epsilon <= firstMaxY)
                || (p2.getX() - epsilon >= firstMinX && p2.getX() + epsilon <= firstMaxX)
                || (p2.getY() - epsilon >= firstMinY && p2.getY() + epsilon <= firstMaxY)) {
            return true;
        }
        return false;
    }

    /**
     * The function will calculate the values of given 2 lines - while one (or two) is vertical.
     * <p>
     * The function will find out in any case, what are the values of tho intersection point- considering the option
     * of 1 or 2 verticals lines: mostly in the slope equation.
     * <p>
     * @param other that represents the line that we want to check (like described above)
     * @param flag that represents the 'case' that we need to check (1-two verticals, 2 or 3- one vertical line)
     * @return the function return value is Geometry.Point - the intersection point */
    public Point verticalLine(Line other, int flag) {
        // we can insert (0,0) values here, just because we know for sure that we will get a common point-
        // the function is working just in cse that we know for sure that there is at least one common point..
        double x = 0;
        double y = 0;

        // the line 'this' is vertical, so we calculate the slope of the other line, then finding the common point.
        if (flag == 1) {
            double slope = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            x = this.start.getX();
            y = (slope * (x - other.start.getX())) + other.start.getY();
        }
        // the line 'other' is vertical, so we calculate the slope of the other line, then finding the common point.
        if (flag == 2) {
            double slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            x = other.start.getX();
            y = (slope * (x - this.start.getX())) + this.start.getY();
        }
        // two lines are verticals, so the 'x' value is clear, and the 'y' value will calculate by split to cases.
        // checking any possible case
        if (flag == 3) {
            x = other.start.getX();
            if (other.start.getY() == this.start.getY()) {
                y = other.start.getY();
            }
            if (other.start.getY() == this.end.getY()) {
                y = other.start.getY();
            }
            if (other.end.getY() == this.start.getY()) {
                y = other.end.getY();
            }
            if (other.end.getY() == this.end.getY()) {
                y = other.end.getY();
            }
        }
        Point intersection = new Point(x, y);
        return intersection;
    }

    /**
     * The function will calculate the values of given 2 lines - while one (or two) is horizon.
     * <p>
     * The function will find out in any case, what are the values of tho intersection point- considering the option
     * of 1 or 2 horizons lines: mostly in the slope equation.
     * <p>
     * @param other that represents the line that we want to check (like described above)
     * @param flag that represents the 'case' that we need to check (1-two horizons, 2 or 3- one horizon line)
     * @return the function return value is Geometry.Point - the intersection point */
    public Point horizonLine(Line other, int flag) {
        //we can insert (0,0) values here, just because we know for sure that we will get a common point-
        //the function is working just in cse that we know for sure that there is at least one common point..
        double x = 0;
        double y = 0;

        // the line 'this' is horizon, so we calculate the slope of the other line, then finding the common point.
        if (flag == 4) {
            double slope = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            y = this.start.getY();
            x = ((y - other.start.getY()) / slope) + other.start.getX();
        }
        // the line 'other' is horizon, so we calculate the slope of the other line, then finding the common point.
        if (flag == 5) {
            double slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            y = other.start.getY();
            x = ((y - this.start.getY()) / slope) + this.start.getX();
        }
        // two lines are horizons, so the 'x' value is clear, and the 'y' value will calculate by split to cases.
        // checking any possible case
        if (flag == 6) {
            y = other.start.getY();
            if (other.start.getX() == this.start.getX()) {
                x = other.start.getX();
            }
            if (other.start.getX() == this.end.getX()) {
                x = other.start.getX();
            }
            if (other.end.getX() == this.start.getX()) {
                x = other.end.getX();
            }
            if (other.end.getX() == this.end.getX()) {
                x = other.end.getX();
            }
        }
        Point intersection = new Point(x, y);
        return intersection;
    }

    /**
     * The function will find the closest intersection point between some line and a given rectangle.
     * <p>
     * The function will find if there is an intersection point (using other functions), and will return the
     * intersection point: if the intersection point does not exist- return null. if there is 2 points - the
     * function will find the closest point to start value of the line, and return it.
     * <p>
     * @param rect that represents rectangle that we want to check
     * @return the function return value is Geometry.Point - the closest intersection point */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line temp = new Line(this.start, this.end);
        List<Point> interPointList = new ArrayList<>();
        // adding the intersection points to the list
        interPointList = rect.intersectionPoints(temp);
        // find the size of the list- to know which if statement is relevant
        int size = interPointList.size();
        // if there is no any intersection point- return null
        if (size == 0) {
            return null;
        }
        // if there is only one intersection point- return it
        if (size == 1) {
            return interPointList.get(0);
        }
        // if there are two intersection points- find the closest one and return it
        if (size == 2) {
            Point check = temp.start;
            // first potential pont
            Point interPoint1 =  interPointList.get(0);
            // second potential pont
            Point interPoint2 =  interPointList.get(1);
            double dist1 = check.distance(interPoint1);
            double dist2 = check.distance(interPoint2);
            // find the smallest distance and return it
            if (dist1 < dist2) {
                return interPoint1;
            }
            if (dist1 > dist2) {
                return interPoint2;
            }
        }
        return null;
    }

}

