// 211970389 Yonatan Zohar
package geometric.objects;

import global.Global;

/**
 * Represents a line in two-dimensional space.
 */
public class Line {
    // Define fields
    private Point start;

    private Point end;

    /**
     * Constructs a line with the given start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Returns a string representation of this Line object.
     *
     * @return A string containing the string representation of the start point
     * and end point of this Line object.
     */
    public String toString() {
        String result = "point start is: " + this.start.toString();
        result = result + "point end is: " + this.end.toString();
        return result;
    }

    /**
     * Constructs a line with the given start and end coordinates.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (this.end.getX() + this.start.getX()) / 2;
        double y = (this.end.getY() + this.start.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line to compare to
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        if ((this.start.equals(other.end)) && (this.end.equals(other.start))) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the direction of a line segment given three points.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @param x3 The x-coordinate of the third point.
     * @param y3 The y-coordinate of the third point.
     * @return The direction of the line segment.
     */
    private double calculateDirection(double x1, double y1, double x2,
                                      double y2, double x3, double y3) {
        return (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);
    }

    /**
     * Checks if a point is on a line segment defined by two endpoints.
     *
     * @param x1 The x-coordinate of the first endpoint of the line segment.
     * @param y1 The y-coordinate of the first endpoint of the line segment.
     * @param x2 The x-coordinate of the second endpoint of the line segment.
     * @param y2 The y-coordinate of the second endpoint of the line segment.
     * @param x3 The x-coordinate of the point to check.
     * @param y3 The y-coordinate of the point to check.
     * @return true if the point is on the line segment, false otherwise.
     */
    private boolean isPointOnLineSegment(double x1, double y1, double x2,
                                         double y2, double x3, double y3) {
        return x3 >= Math.min(x1, x2) && x3 <= Math.max(x1, x2)
                && y3 >= Math.min(y1, y2) && y3 <= Math.max(y1, y2);
    }


    /**
     * Determines if this line segment intersects with another line segment.
     *
     * @param other the other line segment to check for intersection with
     * @return true if the line segments intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // Extract the coordinates of the two line segments
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();

        // Check if both lines are vertical
        if (x1 == x2 && x3 == x4) {
            /* If both vertical lines have the same x-coordinate and overlap in
             the y-coordinate range */

            if ((x1 == x3 && Math.max(y1, y2) >= Math.min(y3, y4))
                    && (Math.min(y1, y2) <= Math.max(y3, y4))) {
                return true;
            }
            return false;
        }

        // Check if line1 is vertical
        if (x1 == x2) {
            double m2 = (y4 - y3) / (x4 - x3);
            double b2 = y3 - m2 * x3;
            double yIntersect = m2 * x1 + b2;

            /* If the y-intercept of line2 lies within the y-coordinate range
             of line1, they intersect */
            if ((yIntersect >= Math.min(y1, y2))
                    && (yIntersect <= Math.max(y1, y2))
                    && (yIntersect >= Math.min(y3, y3))
                    && (yIntersect <= Math.max(y3, y3))) {
                return true;
            }
            return false;
        }

        // Check if line2 is vertical
        if (x3 == x4) {
            double m1 = (y2 - y1) / (x2 - x1);
            double b1 = y1 - m1 * x1;
            double yIntersect = m1 * x3 + b1;

            /* If the y-intercept of line1 lies within the y-coordinate
             range of line2, they intersect  */
            if ((yIntersect >= Math.min(y1, y2))
                    && (yIntersect <= Math.max(y1, y2))
                    && (yIntersect >= Math.min(y3, y3))
                    && (yIntersect <= Math.max(y3, y3))) {
                return true;
            }
            return false;
        }

        // For non-vertical lines, continue with the previous implementation

        // Calculate the direction of the line segments
        double direction1 = calculateDirection(x1, y1, x2, y2, x3, y3);
        double direction2 = calculateDirection(x1, y1, x2, y2, x4, y4);
        double direction3 = calculateDirection(x3, y3, x4, y4, x1, y1);
        double direction4 = calculateDirection(x3, y3, x4, y4, x2, y2);

        /* Check if the line segments have opposite directions,
         indicating an intersection */
        if ((direction1 > 0 && direction2 < 0
                || direction1 < 0 && direction2 > 0)
                && (direction3 > 0 && direction4 < 0
                || direction3 < 0 && direction4 > 0)) {
            return true;
        }

        // Check if the line segments are collinear and overlapping
        if (direction1 == 0 && isPointOnLineSegment(x1, y1, x2, y2, x3, y3)
                || direction2 == 0 && isPointOnLineSegment(x1, y1, x2, y2, x4, y4)
                || direction3 == 0 && isPointOnLineSegment(x3, y3, x4, y4, x1, y1)
                || direction4 == 0 && isPointOnLineSegment(x3, y3, x4, y4, x2, y2)) {
            return true;
        }

        return false;
    }

    /**
     * Calculates the intersection point between this line and another line,
     * if there is only one intersection point.
     *
     * @param other the other line to find the intersection point with
     * @return the intersection point between the two lines, or null if there
     * is no intersection point or more than one intersection point
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        // Handle special case where one of the lines is a point
        if (this.start.equals(this.end)) {
            if (other.isPointOnLine(this.start)) {
                return this.start;
            } else {
                return null;
            }
        }
        if (other.start.equals(other.end)) {
            if (this.isPointOnLine(other.start)) {
                return other.start;
            } else {
                return null;
            }
        }
        // Check if the lines are equal
        if (this.equals(other)) {
            return null;
        }

        // Calculate the denominator for the parametric equations
        double denominator = ((y4 - y3) * (x2 - x1)) - ((x4 - x3) * (y2 - y1));

        // Check if the lines are parallel or coincident
        if (Math.abs(denominator) < Global.EPSILON) {
            return this.intersectionWithWithSameSlope(other);
        }

        // Calculate the intersection point coordinates
        double t1 = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denominator;
        double t2 = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denominator;

        double intersectionX = x1 + t1 * (x2 - x1);
        double intersectionY = y1 + t1 * (y2 - y1);


        // Check if the intersection point is within the bounds of both lines
        if ((intersectionX - Math.min(x1, x2) < -1 * Global.EPSILON)
                || (intersectionX - Math.max(x1, x2) > Global.EPSILON)
                || (intersectionX - Math.min(x3, x4) < -1 * Global.EPSILON)
                || (intersectionX - Math.max(x3, x4) > Global.EPSILON)
                || (intersectionY - Math.min(y1, y2) < -1 * Global.EPSILON)
                || (intersectionY - Math.max(y1, y2) > Global.EPSILON)
                || (intersectionY - Math.min(y3, y4) < -1 * Global.EPSILON)
                || (intersectionY - Math.max(y3, y4) > Global.EPSILON)) {
            return null;
        }
        return new Point(intersectionX, intersectionY);
    }

    /**
     * Calculates the intersection point of two lines that have the same slope.
     *
     * @param other the other line to intersect with
     * @return the intersection point, or null if there is no intersection point
     */
    public Point intersectionWithWithSameSlope(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        // check if the lines contain
        if (this.hasMultipleIntersections(other)) {
            // do nothing
            return null;
        }
        // Handle special case of vertical and horizontal lines
        if (Math.abs(x1 - x2) < Global.EPSILON) {
            if (other.isPointOnLine(new Point(x1, y1))) {
                return new Point(x1, y1);
            }
        } else if (Math.abs(y1 - y2) < Global.EPSILON) {
            if (other.isPointOnLine(new Point(x1, y1))) {
                return new Point(x1, y1);
            }
        } else {
            // check if this line contains other line's start point
            if (this.isPointOnLine(other.start())) {
                return other.start();
            }
            // check if this line contains other line's end point
            if (this.isPointOnLine(other.end())) {
                return other.end();
            }
            // check if other line contains this line's start point
            if (other.isPointOnLine(this.start)) {
                return this.start;
            }
            // check if other line contains this line's end point
            if (other.isPointOnLine(this.end)) {
                return this.end;
            }
        }
        return null;
    }

    /**
     * Checks if a point lies on a line.
     *
     * @param point the point to check
     * @return true if the point lies on the line, false otherwise
     */
    public boolean isPointOnLine(Point point) {
        // Get the coordinates of the start and end points of the line
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        // Get the coordinates of the point to check
        double x = point.getX();
        double y = point.getY();

        // Calculate the slope of the line
        double slope = (y2 - y1) / (x2 - x1);

        // check if the point is or start or end
        if ((this.start.equals(point)) || (this.end.equals(point))) {
            return true;
        }

        // Check if the point is outside the bounds of the line
        if ((x - Math.min(x1, x2) < -1 * Global.EPSILON)
                || (x - Math.max(x1, x2) > Global.EPSILON)) {
            return false;
        }
        if ((y - Math.min(y1, y2) < -1 * Global.EPSILON)
                || (y - Math.max(y1, y2) > Global.EPSILON)) {
            return false;
        }

        // Check if the y-coordinate of the point is on the line
        double check;
        if (x1 == x2) {
            return true;
        } else if (x1 < x2) {
            /* If the line is sloping up from left to right,
             use the x-coordinate of the start point */
            check = (x - x1) * slope + y1;
        } else {
            /* If the line is sloping down from left to right,
             use the x-coordinate of the end point */
            check = (x - x2) * slope + y2;
        }

        /* Check if the difference between the y-coordinate of the point
           and the calculated y-coordinate is within EPSILON */
        return (Math.abs(check - y) < Global.EPSILON);
    }

    /**
     * Checks if this line intersects with another line at multiple points.
     *
     * @param other The other line to check for intersections.
     * @return true if this line intersects with the other line
     * at multiple points, false otherwise.
     */
    private boolean hasMultipleIntersections(Line other) {
        // Extracting coordinates of the points of both lines
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        // Calculating slopes of both lines
        double slope1 = (y2 - y1) / (x2 - x1);
        double slope2 = (y4 - y3) / (x4 - x3);

        /* Checking if the end point of this line is equal
         to the start or end point of the other line  */
        if (this.end.getX() > this.start.getX()) {
            if (other.end().getX() > other.start().getX()) {
                if (this.end.equals(other.start())) {
                    return false;
                }
            } else {
                if (this.end.equals(other.end())) {
                    return false;
                }
            }
        } else {
            if (other.end().getX() > other.start().getX()) {
                if (this.start.equals(other.start())) {
                    return false;
                }
            } else {
                if (this.start.equals(other.end())) {
                    return false;
                }
            }
        }

        /* Checking if the end point of the other line is equal to the start
         or end point of this line */
        if (other.end().getX() > other.start().getX()) {
            if (this.end.getX() > this.start.getX()) {
                if (other.end().equals(this.start)) {
                    return false;
                }
            } else {
                if (other.end().equals(this.end)) {
                    return false;
                }
            }
        } else {
            if (this.end.getX() > this.start.getX()) {
                if (other.start().equals(this.start)) {
                    return false;
                }
            } else {
                if (other.start().equals(this.end)) {
                    return false;
                }
            }
        }

        //Checking if the start or end point of this line lies on the other line
        if (this.isPointOnLine(other.start())) {
            if (this.isPointOnLine(new Point(x3 + Global.EPSILON,
                    y3 + slope2 * Global.EPSILON))) {
                return true;
            }
        }
        if (this.isPointOnLine(other.end())) {
            if (this.isPointOnLine(new Point(x4 + Global.EPSILON,
                    y4 + slope2 * Global.EPSILON))) {
                return true;
            }
        }

        //Checking if the start or end point of the other line lies on this line
        if (other.isPointOnLine(this.start)) {
            if (other.isPointOnLine(new Point(x1 + Global.EPSILON,
                    y1 + slope1 * Global.EPSILON))) {
                return true;
            }
        }
        if (other.isPointOnLine(this.end)) {
            if (other.isPointOnLine(new Point(x2 + Global.EPSILON,
                    y2 + slope1 * Global.EPSILON))) {
                return true;
            }
        }

        // If no multiple intersections are found, return false
        return false;
    }

    /**
     * Returns the closest intersection point of this line with a given
     * rectangle to its start point.
     * If no intersection points exist, returns null.
     *
     * @param rect the rectangle to check for intersection with the line
     * @return the closest intersection point of the line with the rectangle to
     * its start point, or null if there is no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // get the intersection points between the line and the rectangle
        java.util.List<Point> list = rect.intersectionPoints(new Line(
                this.start, this.end));

        // if there are no intersection points, return null
        if (list.size() == 0) {
            return null;
        }

        /* initialize the closest intersection point and its distance to the
         start point of the line */
        Point answer = list.get(0);
        double distance = answer.distance(this.start);

        /* iterate over the intersection points, and update the closest
         intersection point if a closer one is found */
        for (int i = 1; i < list.size(); i++) {
            if (distance > list.get(i).distance(this.start)) {
                answer = list.get(i);
                distance = answer.distance(this.start);
            }
        }
        return answer;
    }
}