// 211970389 Yonatan Zohar
package geometric.objects;

import java.util.ArrayList;
/**
 * Represents a rectangle object in 2D space.
 */
public class Rectangle {
    // define fields
    private Point upperLeft;
    private double width;
    private double height;
    private Line upLine;
    private Line downLine;
    private Line rightLine;
    private Line leftLine;
    /**
     * Constructs a rectangle with the specified upper left point,
     * width, and height.
     * @param upperLeft the upper left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        // initialize the lines of the rectangle
        linesOfMakeTheRectangle();
    }
    /**
     * Calculates and sets the lines of the rectangle based on its upper
     * left point, width, and height.
     */
    private void linesOfMakeTheRectangle() {
        this.upLine = new Line(upperLeft, new Point(upperLeft.getX()
                + this.width, upperLeft.getY()));
        this.downLine = new Line(new Point(upperLeft.getX(), upperLeft.getY()
                + this.height), new Point(upperLeft.getX() + this.width,
                upperLeft.getY() + this.height));
        this.leftLine = new Line(upperLeft, new Point(upperLeft.getX(),
                upperLeft.getY() + this.height));
        this.rightLine = new Line(new Point(upperLeft.getX() + this.width,
                upperLeft.getY()), new Point(upperLeft.getX() + this.width,
                upperLeft.getY() + this.height));
    }
    /**
     * Returns a list of intersection points between the rectangle and
     * the given line.
     * @param line the line to check for intersection points with the rectangle
     * @return a list of intersection points between the rectangle
     * and the given line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list = new ArrayList<>();
        // check the intersection with the rectangles walls
        Point tmp = line.intersectionWith(this.upLine);
        if (tmp != null) {
            list.add(tmp);
        }
        tmp = line.intersectionWith(this.downLine);
        if (tmp != null) {
            list.add(tmp);
        }
        tmp = line.intersectionWith(this.leftLine);
        if (tmp != null) {
            list.add(tmp);
        }
        tmp = line.intersectionWith(this.rightLine);
        if (tmp != null) {
            list.add(tmp);
        }
        // return the intersection point
        return list;
    }
    /**
     * Returns the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns the upper-left corner of the rectangle.
     *
     * @return the upper-left corner of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle
     */
    public Line getDownLine() {
        return downLine;
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLeftLine() {
        return leftLine;
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        return rightLine;
    }
    /**
     * Getter method for the line representing the top side of the rectangle.
     * @return the line representing the top side of the rectangle
     */
    public Line getUpLine() {
        return upLine;
    }
    /**
     * Method to check if a given point is contained within the rectangle.
     * @param p the point to check
     * @return true if the point is contained within the rectangle,
     * false otherwise
     */
    public boolean pointContained(Point p) {
        double x = p.getX();
        double y = p.getY();
        double x1 = upperLeft.getX();
        double y1 = upperLeft.getY();
        double x2 = x1 + width;
        double y2 = y1 + height;

        // Check if the point is within the bounds of the rectangle
        return (x > x1 && x < x2) && (y > y1 && y < y2);
    }
    /**
     * This method finds the closest point on the walls of a rectangular area
     * to the point passed as parameter.
     * It returns the point on the wall that is closest to the given point.
     *
     * @param p the point to find the closest wall point to
     * @return the closest point on the wall to the given point
     */
    public Point closePointOnWallsToPointContained(Point p) {
        // create points representing the endpoints of each wall
        Point up = new Point(p.getX(), this.upLine.end().getY());
        Point down = new Point(p.getX(), this.downLine.end().getY());
        Point left = new Point(this.leftLine.end().getX(), p.getY());
        Point right = new Point(this.rightLine.end().getX(), p.getY());

        // put all the wall points in an array
        Point[] array = {up, down, left, right};

        // find the distance between the given point and the closest wall point
        double min = p.distance(array[0]);
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (min > p.distance(array[i])) {
                min = p.distance(array[i]);
                index = i;
            }
        }

        // return the closest wall point
        return array[index];
    }
    /**
     *  method to set the lines of the rectangle based on its height, width,
     *  and upper left point.
     *  use in the method if the object move.
     */
    public void setLines() {
        this.linesOfMakeTheRectangle();
    }

}