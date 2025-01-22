// 211970389 Yonatan Zohar
package geometric.objects;

/**
 * Velocity represents the change in position on the x and the y coordinate.
 */
public class Velocity {
    // magic variables
    private static final int ANGLE_FOR_CALCULATION = 270;

    // Define fields
    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx the change in position on the x-axis.
     * @param dy the change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Create a new geometric.Velocity object from an angle and a speed.
     * @param angle the angle of the velocity.
     * @param speed the speed of the velocity.
     * @return a new geometric.Velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(
                angle + ANGLE_FOR_CALCULATION));
        double dy = speed * Math.sin(Math.toRadians(
                angle + ANGLE_FOR_CALCULATION));
        return new Velocity(dx, dy);
    }
    /**
     * Calculates the magnitude of the velocity vector using the
     * Pythagorean theorem.
     *
     * @return the magnitude of the velocity vector
     */
    public double magnitudeVelocity() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.getDy(), 2));
    }


    /**
     * Take a point with position (x,y) and return a new point with position
     * (x+dx, y+dy).
     *
     * @param p the point to apply the velocity to.
     * @return a new geometric.Point object with the new position
     * after applying the velocity.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Get the change in position on the x-axis.
     * @return the change in position on the x-axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Get the change in position on the y-axis.
     * @return the change in position on the y-axis.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Set the change in position on the x-axis.
     * @param dx the new value for the change in position on the x-axis.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Set the change in position on the y-axis.
     * @param dy the new value for the change in position on the y-axis.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
}
