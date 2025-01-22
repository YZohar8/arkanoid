// 211970389 Yonatan Zohar

package geometric.objects;
import Game.interfaces.Sprite;
import Game.objects.Block;
import Game.objects.CollisionInfo;
import Game.objects.GameEnvironment;
import biuoop.DrawSurface;
import global.Global;
import java.awt.Color;

/**
 * A class that represents a ball.
 */
public class Ball implements Sprite {
    // define magic variables
    private static final int SIZE_SMALL_BALL_INTO_THE_BALL = 2;
    private static final Color COLOR_SMALL_BALL_INTO_THE_BALL = Color.red;


    // Define fields
    private Point center;
    private int size;
    private java.awt.Color color;
    private Velocity velocity;
    private Point windowStart;
    private Point windowEnd;
    private GameEnvironment gameEnvironment;


    /**
     * Constructs a new ball given its center point, radius and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }
    /**
     * Constructs a new ball given its center point, radius and color.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     * @param gameEnvironment object with list of collidable.
     */
    public Ball(Point center, int r, java.awt.Color color,
                GameEnvironment gameEnvironment) {
        this.center = new Point(center.getX(), center.getY());
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Returns the x-coordinate of the center point of the ball.
     *
     * @return the x-coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * Returns the y-coordinate of the center point of the ball.
     *
     * @return the y-coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * Returns the size of the ball.
     *
     * @return the size of the ball
     */
    public int getSize() {
        return this.size;
    }
    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * Sets the velocity of the ball to the given velocity.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball to the given dx and dy values.
     *
     * @param dx the velocity in the x-direction
     * @param dy the velocity in the y-direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * Checks the velocity of the ball and updates it based on collision with
     * walls and move the next step according the velocity of the ball
     * after the change.
     */
    public void moveOneStep() {
        boolean flag = false;
        Line lineMoveTheBall = new Line(this.center, new Point(
                this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy()));
        Point intersectionPoint = null;
        Line board;

        // Check if the ball hits the right wall and if so, change the velocity
        if ((this.center.getX() + this.velocity.getDx() + size)
                >= windowEnd.getX()) {
            // Create a line representing the right wall of the window
            board = new Line(this.windowEnd.getX() - this.size,
                    this.windowStart.getY(), this.windowEnd.getX()
                    - this.size, this.windowEnd.getY());
            // Get the intersection point of the ball's path with the right wall
            intersectionPoint = board.intersectionWith(lineMoveTheBall);
            flag = true;
            // Reverse horizontal velocity to bounce off the right wall
            this.velocity.setDx(-1 * this.velocity.getDx());
            // Check if the ball hits the left wall and if so, change velocity
        } else if ((this.center.getX() + this.velocity.getDx() - size)
                <= windowStart.getX()) {
            // Create a line representing the left wall of the window
            board = new Line(this.windowStart.getX() + this.size,
                    this.windowStart.getY(), this.windowStart.getX()
                    + this.size, this.windowEnd.getY());
            // Get the intersection point of the ball's path with the left wall
            intersectionPoint = board.intersectionWith(lineMoveTheBall);
            flag = true;
            // Reverse horizontal velocity to bounce off the left wall
            this.velocity.setDx(-1 * this.velocity.getDx());
            // Check if the ball hits the bottom wall and if so,change  velocity
        }
          if ((this.center.getY() + this.velocity.getDy() - size)
                <= windowStart.getY()) {
            if (!flag) {
                // Create a line representing the top wall of the window
                board = new Line(this.windowStart.getX(),
                        this.windowStart.getY()
                                + this.size, this.windowEnd.getX(),
                        this.windowStart.getY() + this.size);
                // Get the intersection point of the ball path with the top wall
                intersectionPoint = board.intersectionWith(lineMoveTheBall);
                flag = true;
            }
            // Reverse vertical velocity to bounce off the top wall
            this.velocity.setDy(-1 * this.velocity.getDy());
        }
        /* If no wall was hit, or if no intersection point was found,
           move the ball one step in its current velocity direction */
        if (!flag || intersectionPoint == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // Update the ball's center if is not touching in the board
            this.center = intersectionPoint;
        }
    }
    /**
     * Sets the end point of the window.
     *
     * @param x The x-coordinate of the end point.
     * @param y The y-coordinate of the end point.
     */
    public void setWindowEnd(double x, double y) {
        this.windowEnd = new Point(x, y);
    }

    /**
     * Sets the start point of the window.
     *
     * @param x The x-coordinate of the start point.
     * @param y The y-coordinate of the start point.
     */
    public void setWindowStart(double x, double y) {
        this.windowStart = new Point(x, y);
    }

    /**
     * Checks if the given coordinates are equal to the start and end
     * points of the window.
     *
     * @param x1 The x-coordinate of the first point to compare.
     * @param y1 The y-coordinate of the first point to compare.
     * @param x2 The x-coordinate of the second point to compare.
     * @param y2 The y-coordinate of the second point to compare.
     * @return True if the given coordinates are equal to the
     * start and end points of the window, false otherwise.
     */
    public boolean equalBoardWindow(double x1, double y1, double x2,
                                    double y2) {
        return (((new Point(x1, y1).equals(this.windowStart))
                && (new Point(x2, y2).equals(this.windowEnd)))
                || ((new Point(x1, y1).equals(this.windowEnd))
                && (new Point(x2, y2).equals(this.windowStart))));
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle(this.getX(), this.getY(), size);
        d.setColor(COLOR_SMALL_BALL_INTO_THE_BALL);
        d.fillCircle(this.getX(), this.getY(), SIZE_SMALL_BALL_INTO_THE_BALL);
    }

    @Override
    public void timePassed() {
        // Calculate the ball's trajectory and find the closest collision point
        Line trajectory = new Line(this.center, new Point(this.getX()
                + this.velocity.getDx(), this.getY()
                + this.velocity.getDy()));
        CollisionInfo closestCollision = this.gameEnvironment.
                getClosestCollision(trajectory);

        // If no collision is detected, move the ball one step
        if (closestCollision == null) {
            this.moveOneStep();
        } else {
            // else place the ball's center at the collision point
            this.center = closestCollision.collisionPoint();
            // Update the ball's velocity based on the object it collided with
            Velocity newVelocity;
            try {
                Block block = (Block) closestCollision.collisionObject();
                newVelocity = block.hit(this, this.center, this.velocity);
            } catch (Exception e) {
                newVelocity = closestCollision.collisionObject().hit(
                        this.center, this.velocity);
            }

            /* Move the ball's center slightly to the side of the collision
             point to avoid getting stuck */
            int dX, dY;
            dX = newVelocity.getDx() > 0 ? 1 : -1;
            dY = newVelocity.getDy() > 0 ? 1 : -1;
            this.center = new Point(this.center.getX()
                    + (dX * Global.IS_NOT_ON_LINE),
                    this.center.getY() + (dY * Global.IS_NOT_ON_LINE));
            this.velocity = newVelocity;
        }
    }

    /**
     * the function set the center Point.
     * @param p Point it is new center point
     */
    public void setCenter(Point p) {
        this.center = p;
    }


    /**
     * Connects the object to the game environment.
     * The object will use the specified game environment for interactions.
     *
     * @param gameEnvironment the game environment to connect to
     */
    public void connectToGame(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

}

