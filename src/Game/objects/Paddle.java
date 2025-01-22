// 211970389 Yonatan Zohar
package Game.objects;

import Game.animtion.GameLevel;
import Game.interfaces.Collidable;
import Game.interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometric.objects.Ball;
import geometric.objects.Point;
import geometric.objects.Velocity;
import global.Global;

import java.awt.Color;

/**
 * This class represents a paddle, which is a type of sprite and a type of
 * collidable object in the game.
 * The paddle can move to the left or right and can be hit by a ball to
 * change its direction.
 */
public class Paddle implements Sprite, Collidable {
    // define magic variables
    private static final int HOW_MUCH_PARTS = 5;
    private static final int[] SET_VELOCITY = {300, 330, 0, 30, 60};
    // define fields
    private double leftWalls;
    private double rightWalls;
    private Block paddle;
    private double sizePartOfPaddle;
    private double speed;
    private biuoop.KeyboardSensor keyboard;
    private java.util.List<Ball> balls;

    /**
     * Constructor for Paddle.
     *
     * @param upperLeft  the upper left point of the paddle
     * @param width      the width of the paddle
     * @param height     the height of the paddle
     * @param speed      the speed of the paddle
     * @param leftWalls  the left wall that the paddle can move until
     * @param rightWalls the right wall that the paddle can move until
     */
    public Paddle(Point upperLeft, double width, double height, double speed,
                  double leftWalls, double rightWalls) {
        Color color = Global.createRandomColor();
        this.sizePartOfPaddle = width / HOW_MUCH_PARTS;
        this.paddle = new Block(upperLeft, width, height, color);
        this.speed = speed;
        this.leftWalls = leftWalls;
        this.rightWalls = rightWalls;
    }

    /**
     * Constructor for Paddle.
     *
     * @param upperLeft  the upper left point of the paddle
     * @param width      the width of the paddle
     * @param height     the height of the paddle
     * @param speed      the speed of the paddle
     * @param color      the color of the paddle
     * @param leftWalls  the left wall that the paddle can move until
     * @param rightWalls the right wall that the paddle can move until
     */
    public Paddle(Point upperLeft, double width, double height, double speed,
                  java.awt.Color color, double leftWalls, double rightWalls) {
        this.sizePartOfPaddle = width / HOW_MUCH_PARTS;
        this.paddle = new Block(upperLeft, width, height, color);
        this.speed = speed;
        this.leftWalls = leftWalls;
        this.rightWalls = rightWalls;
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        // If the next step takes us out of the screen's borders
        if (this.paddle.getCollisionRectangle().getUpperLeft().getX()
                - this.speed < leftWalls) {
            this.paddle.getCollisionRectangle().getUpperLeft().setX(leftWalls);
        /* You can take the next step, then the object will move according
         to its speed */
        } else {
            this.paddle.getCollisionRectangle().getUpperLeft().setX(
                    this.paddle.getCollisionRectangle().getUpperLeft().getX()
                            - speed);
        }
        // update location
        paddle.getCollisionRectangle().setLines();
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        // If the next step takes us out of the screen's borders
        if (this.paddle.getCollisionRectangle().getUpperLeft().getX() + speed
                + this.paddle.getCollisionRectangle().getWidth() > rightWalls) {
            this.paddle.getCollisionRectangle().getUpperLeft().setX(
                    rightWalls - this.paddle.getCollisionRectangle()
                            .getWidth());
            /* You can take the next step, then the object will move according
            to its speed */
        } else {
            this.paddle.getCollisionRectangle().getUpperLeft().setX(
                    this.paddle.getCollisionRectangle().getUpperLeft().getX()
                            + speed);
        }
        // update location
        paddle.getCollisionRectangle().setLines();
    }

    // interfaces Sprite
    @Override
    public void timePassed() {
        // check if pressed key
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        } else if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    // interfaces Collidable
    @Override
    public geometric.objects.Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    @SuppressWarnings("checkstyle:RightCurly")
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // Create a new Velocity with the same values as the current velocity.
        Velocity newVelocity = currentVelocity;
        // Check if the collision point is on the left line of the paddle.
        if (this.paddle.getCollisionRectangle().getLeftLine().
                isPointOnLine(collisionPoint)) {
            newVelocity = new Velocity(currentVelocity.getDx() * -1,
                    currentVelocity.getDy());
            // Check if the collision point is on the right line of the paddle.
        } else if (this.paddle.getCollisionRectangle().getRightLine()
                .isPointOnLine(collisionPoint)) {
            newVelocity = new Velocity(currentVelocity.getDx() * -1,
                    currentVelocity.getDy());
            // impact with down line
        } else if (this.paddle.getCollisionRectangle().getDownLine().
                isPointOnLine(collisionPoint)) {
                newVelocity = new Velocity(currentVelocity.getDx(),
                        Math.abs(currentVelocity.getDy()) * -1);
            // If the collision point is on up line
        } else {
            // Iterate over all the possible collision points on the top paddle.
            for (int i = 0; i < HOW_MUCH_PARTS; i++) {
                // Check if the collision point is in the i-th section of the paddle.
                if ((collisionPoint.getX() >= this.paddle.
                        getCollisionRectangle().getUpperLeft().getX()
                        + i * this.sizePartOfPaddle)
                        && (collisionPoint.getX() <= this.paddle.
                        getCollisionRectangle().getUpperLeft().getX()
                        + (i + 1) * this.sizePartOfPaddle)) {
                    /* Set the new velocity to a specific value, based on the
                     i-th section of the paddle. */
                    newVelocity = Velocity.fromAngleAndSpeed(SET_VELOCITY[i],
                            currentVelocity.magnitudeVelocity());
                    break;
                }
            }
        }

        // Return the new velocity.
        return newVelocity;
    }

    /**
     * This method adds the paddle to the game that receive.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        this.keyboard = g.getBoardGame().getKeyboardSensor();
        g.addCollidable(this);
        g.addSprite(this);
        balls = g.getBalls();
    }
}
