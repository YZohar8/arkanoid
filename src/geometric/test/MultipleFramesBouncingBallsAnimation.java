// 211970389 Yonatan Zohar

package geometric.test;
import  global.Global;
import biuoop.DrawSurface;
import biuoop.GUI;
import geometric.objects.Ball;
import geometric.objects.Point;
import geometric.objects.Velocity;

import java.awt.Color;
import java.util.Random;

/**
 * A class for creating a multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    // magic variables
    private static final int SIZE_BIG_WINDOW = 800;
    private static final int POINT_START_1 = 50;
    private static final int POINT_END_1 = 500;
    private static final int SIZE1 = 450;
    private static final int POINT_START_2 = 450;
    private static final int POINT_END_2 = 600;
    private static final int SIZE2 = 150;
    private static final int MAX_SPEED = 65;
    private static final int RADIOS_TO_FLAG = 50;
    private static final String DEFAULT_RADIOS = "10";

    /**
     * Creates an array of geometric.Ball geometric.objects with the given sizes. and set the
     * borders of the window in which the ball is drawn
     *
     * @param args An array of Strings representing the sizes of the balls.
     * @return An array of geometric.Ball geometric.objects.
     */
    public Ball[] createBallArrayWithKnownSize(String[] args) {
        //Setting Variables
        MultipleBouncingBallsAnimation m = new MultipleBouncingBallsAnimation();
        int middle = args.length / 2;
        Ball[] array = new Ball[args.length];
        // Loop over some of the values we received
        for (int i = 0; i < array.length; i++) {
            // create new ball with random coordinate, color. and known radios.
            Color randColour = m.createRandomColor();
            // check if it is the first half in the array or the second half
            if (i < middle) {
                // create new ball
                array[i] = new Ball(Point.createRandomPoint(POINT_START_1,
                        POINT_START_1, SIZE1, Integer.parseInt(args[i])),
                        Integer.parseInt(args[i]), randColour);
                // Set the drawing boundaries of the ball
                array[i].setWindowStart(POINT_START_1, POINT_START_1);
                array[i].setWindowEnd(POINT_END_1, POINT_END_1);
            } else {
                // create new ball
                array[i] = new Ball(Point.createRandomPoint(POINT_START_2,
                        POINT_START_2, SIZE2, Integer.parseInt(args[i])),
                        Integer.parseInt(args[i]), randColour);
                // Set the drawing boundaries of the ball
                array[i].setWindowStart(POINT_START_2, POINT_START_2);
                array[i].setWindowEnd(POINT_END_2, POINT_END_2);
            }

        }
        return array;
    }

    /**
     * Draws multiple balls with multiple frames on a GUI window.
     * Balls are moved one step at a time and drawn on a DrawSurface object
     * Balls are moved one step at a time and drawn on a DrawSurface object
     * obtained from a GUI object. The balls are drawn in two halves of the
     * window,separated by two rectangles, one gray and one yellow.
     *
     * @param array An array of geometric.Ball geometric.objects representing the balls to be drawn.
     */
    public void drawManyAnimationWithManyFrames(Ball[] array) {
        // create a new GUI object
        GUI gui = new GUI("title", SIZE_BIG_WINDOW, SIZE_BIG_WINDOW);
        DrawSurface d;
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // loop indefinitely
        while (true) {
            // move all balls one step
            for (Ball ball : array) {
                ball.moveOneStep();
            }
            // get a new DrawSurface object from the GUI
            d = gui.getDrawSurface();
            // fill a gray rectangle to
            d.setColor(Color.gray);
            d.fillRectangle(POINT_START_1, POINT_START_1, SIZE1, SIZE1);
            // draw all balls in the first helf in array on the DrawSurface
            for (Ball ball : array) {
                if (ball.equalBoardWindow(POINT_START_1, POINT_START_1,
                        POINT_END_1, POINT_END_1)) {
                    ball.drawOn(d);
                }
            }
            // fill a yellow rectangle to create a background for the balls
            d.setColor(Color.yellow);
            d.fillRectangle(POINT_START_2, POINT_START_2, SIZE2, SIZE2);
            // draw all balls in the second helf array on the DrawSurface
            for (Ball ball : array) {
                if (ball.equalBoardWindow(POINT_START_2, POINT_START_2,
                        POINT_END_2, POINT_END_2)) {
                    ball.drawOn(d);
                }
            }
            // show the new frame on the GUI
            gui.show(d);
            // pause for 50 milliseconds before starting the next frame
            sleeper.sleepFor(Global.TIME_WAIT);
        }
    }

    /**
     * Sets random velocities to an array of geometric.Ball geometric.objects.
     * based on size of ball radius. if ball radius bigger than RADIOS_TO_FLAG
     * the speed is equal to ball with radios size - RADIOS_TO_FLAG.
     * the angle is random.
     *
     * @param array An array of geometric.Ball geometric.objects.
     */
    public void setVelocityToArrays(Ball[] array) {
        Random rand = new Random();
        double speed, angle;
        // define variables
        for (Ball ball : array) {
            // create a new geometric.Velocity object with the generated speed and angle
            angle = rand.nextDouble() * Global.ANGLE;
            // if radius ball is smaller than the radius flag
            if (ball.getSize() < RADIOS_TO_FLAG) {
                speed = (double) MAX_SPEED / ball.getSize();
            } else {
                speed = (double) MAX_SPEED / RADIOS_TO_FLAG;
            }
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
        }
    }

    /**
     * Performs data integrity checks on an array of arguments.
     *
     * @param args   The array of arguments to be checked and modified.
     * @param middle The middle index used as a reference for comparison.
     * @param size1  The first size used for comparison.
     * @param size2  The second size used for comparison.
     */
    public void dataIntegrityCheck(String[] args, int middle, int size1,
                                   int size2) {
        Random rand = new Random();
        for (int i = 0; i < args.length; i++) {
            try {
                int tmp = Integer.parseInt(args[i]);
                if (((tmp > (size2 / 2)) || (tmp <= 0)) && (i > middle)) {
                    /* If argument is greater than half of size2 and index is
                     greater than middle, replace with random number */

                    System.out.println("You have entered wrong radius."
                            + " So it was replaced by a random number.");
                    args[i] = Integer.toString(rand.nextInt(RADIOS_TO_FLAG)
                            + 1);
                } else if (((tmp > (size1 / 2)) || (tmp <= 0))
                        && (i <= middle)) {
                    /* If argument is greater than half of size1 and index is
                     less than or equal to middle, replace with random number */
                    System.out.println("You have entered wrong radius."
                            + " So it was replaced by a random number.");
                    args[i] = Integer.toString(rand.nextInt(RADIOS_TO_FLAG)
                            + 1);
                }
            } catch (Exception e) {
                // If the argument is not an int replace it with a random number
                System.out.println("The entered data is not an int. Therefore,"
                        + " a random number was inserted in its place.");
                args[i] = Integer.toString(rand.nextInt(RADIOS_TO_FLAG) + 1);
            }
        }
    }


    /**
     * The main method of the program, which creates array of geometric.Ball geometric.objects,
     * sort is by radius using the bubble sort algorithm,
     * sets random velocities to each ball, and then animates the bouncing balls
     * in multiple frames.
     *
     * @param args an array of command-line arguments, which will be used to
     *             create the arrays of geometric.Ball geometric.objects
     */
    public static void main(String[] args) {
        // if args is empty create new array
        if (args.length == 0) {
            System.out.println("You didn't put anything in, so one default"
                    + " size ball was drawn.");
            args = new String[1];
            args[0] = DEFAULT_RADIOS;
        }
        MultipleFramesBouncingBallsAnimation m = new
                MultipleFramesBouncingBallsAnimation();
        // calculate the middle index of the command-line arguments array
        int middle = args.length / 2;
        m.dataIntegrityCheck(args, middle, SIZE1, SIZE2);
        // create the first and the second ball array
        Ball[] array = m.createBallArrayWithKnownSize(args);
        // set random velocities
        m.setVelocityToArrays(array);
        // animate the bouncing balls in multiple frames
        m.drawManyAnimationWithManyFrames(array);
    }
}