// 211970389 Yonatan Zohar
package geometric.objects;
import  global.Global;
import java.util.Random;

/**
 * Point represents a point in a two-dimensional space with
 * x and y coordinates.
 */
public class Point {
    // Define fields
    private double x;

    private double y;

    /**
     * This constructor creates a geometric.Point object with the
     * specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a string representation of this point.
     *
     * @return a string in the format "X: x-value Y: y-value."
     */
    public String toString() {
        return "X: " + this.x + " Y: " + this.y + ".";
    }

    /**
     * This method creates a random geometric.Point object with x and y
     * coordinates that fall within a certain range.
     *
     * @param high   the maximum value of the y-coordinate range
     * @param wide   the maximum value of the x-coordinate range
     * @param radios the radius of the ball
     * @return a geometric.Point object with random x and y coordinates
     */
    public static Point createRandomPoint(int high, int wide, int radios) {
        Random rand = new Random();
        double x = rand.nextDouble() * (wide - 2 * radios) + radios;
        double y = rand.nextDouble() * (high - 2 * radios) + radios;
        return new Point(x, y);
    }

    /**
     * This method creates a random geometric.Point object with x and
     * y coordinates that fall within a certain range.
     *
     * @param startX the starting x-coordinate of the range
     * @param startY the starting y-coordinate of the range
     * @param size   the size of the range
     * @param radios the radius of the point
     * @return a geometric.Point object with random x and y coordinates
     */
    public static Point createRandomPoint(int startX, int startY, int size,
                                          int radios) {
        Random rand = new Random();
        double x = rand.nextDouble() * (size - 2 * radios) + startX + radios;
        double y = rand.nextDouble() * (size - 2 * radios) + startY + radios;
        return new Point(x, y);
    }

    /**
     * Returns the distance between this point to another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double result = Math.pow(this.x - other.getX(), 2);
        result += Math.pow(this.y - other.getY(), 2);
        return Math.sqrt(result);
    }

    /**
     * Returns true if the points are equal, false otherwise.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if ((Math.abs(this.x - other.getX()) <= Global.EPSILON)
                && Math.abs(this.y - other.getY()) <= Global.EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x The x-coordinate to set.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y The y-coordinate to set.
     */
    public void setY(double y) {
        this.y = y;
    }

}
