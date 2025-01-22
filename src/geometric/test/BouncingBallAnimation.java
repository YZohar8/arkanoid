// 211970389 Yonatan Zohar
package geometric.test;

import biuoop.DrawSurface;
import biuoop.GUI;
import geometric.objects.Ball;
import geometric.objects.Point;
import global.Global;

import java.util.Random;

/**
 * The geometric.geometricTest.BouncingBallAnimation class is responsible for creating and running a
 * bouncing ball animation on a GUI window.
 */
public class BouncingBallAnimation {
    // magic variables
    private static final int RADIOS = 30;
    private static final int POINT_START = 0;
    private static final int POINT_END = 200;
    private static final int MAX_SPEED = 60;
    private static final int EXPECTED_SIZE_FOR_ARRAY_ARGS = 4;
    private static final String DEFAULT_TO_ARRAY_ARGS_LOCATION = "50";
    private static final String DEFAULT_TO_ARRAY_ARGS_SPEED = "10";


    /**
     * Draws an animation of a ball moving at a given velocity from a given
     * starting point.
     *
     * @param start the starting point of the ball
     * @param dx    the horizontal velocity of the ball
     * @param dy    the vertical velocity of the ball
     */
    public void drawAnimation(Point start, double dx, double dy) {
        // create a GUI window with a size of 200x200 pixels
        GUI gui = new GUI("title", POINT_END, POINT_END);
        // create a sleeper to pause the animation
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        /* create a ball with radius 30 and black color at the given
           start coordinates and set the velocity */

        Point p1 = new Point(start.getX(), start.getY());
        Ball ball = new Ball(p1, RADIOS, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        // set the borders of the window in which the ball is drawn
        ball.setWindowStart(POINT_START, POINT_START);
        ball.setWindowEnd(POINT_END, POINT_END);
        // infinite loop to continuously move and redraw the ball
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            // draw the ball on the draw surface and shoe it
            ball.drawOn(d);
            gui.show(d);
            // wait for 50 milliseconds to slow down the animation
            sleeper.sleepFor(Global.TIME_WAIT);
        }
    }

    /**
     * If all the necessary data is not received in the args array,
     * an array of the appropriate size is created and default values
     * are inserted in the places that need to be filled.
     *
     * @param args The input array of strings that receive from the user
     * @return string[] this is the new array tat created in the function
     */
    public String[] fillArrayArgsWithNecessaryData(String[] args) {
        int k = 0;
        String[] tmp = new String[EXPECTED_SIZE_FOR_ARRAY_ARGS];
        try {
            for (k = 0; k < EXPECTED_SIZE_FOR_ARRAY_ARGS; k++) {
                // check if exists args[k] if not is throw error
                String test = args[k];
            }
        } catch (Exception e) {
            System.out.println("You did not enter enough arguments,"
                    + " so the default was entered instead.");
            // create new array with all data
            for (int j = 0; j < tmp.length; j++) {
                if (j < k) {
                    tmp[j] = args[j];
                } else {
                    if (j < EXPECTED_SIZE_FOR_ARRAY_ARGS / 2) {
                        tmp[j] = DEFAULT_TO_ARRAY_ARGS_LOCATION;
                    } else {
                        tmp[j] = DEFAULT_TO_ARRAY_ARGS_SPEED;
                    }
                }
            }
            args = tmp;
        }
        return args;
    }

    /**
     * Performs data integrity checks on an array of input strings
     * representing integer values.
     * If incorrect input is detected, random values within valid ranges
     * are generated and replaced.
     *
     * @param args The input array of strings that receive from the user
     */
    public void dataIntegrityCheck(String[] args) {
        // Checks if the input is correct
        Random rand = new Random();
        for (int i = 0; i < args.length; i++) {
            try {
                int tmp = Integer.parseInt(args[i]);
                // Checks if an off-screen location has been entered
                if (((tmp < POINT_START + RADIOS + 1)
                        || (tmp > POINT_END - RADIOS - 1)) && (i < 2)) {
                    System.out.println("Incorrect location entered. "
                            + "Then another location was randomly entered");
                    // Generates a random in the board
                    args[i] = Integer.toString(rand.nextInt(
                            POINT_END - RADIOS - RADIOS) + RADIOS);
                }
                // Checks if a speed value is outside the allowed range
                if ((i >= 2) && ((tmp > MAX_SPEED) || (tmp < -1 * MAX_SPEED))) {
                    System.out.println("A wrong speed than allowed was "
                        + "entered. A random velocity was prepared instead.");
                    // Generates a random integer within the range of MAX_SPEED
                    args[i] = Integer.toString(rand.nextInt(MAX_SPEED));
                }
            } catch (Exception e) {
                // Handles the case when a non-integer input is entered
                System.out.println("Entering an input that is not an int."
                   + " Therefore, a random number was inserted in its place");
                if (i < 2) {
                    // Generates a random in the board
                    args[i] = Integer.toString(rand.nextInt(
                            POINT_END - RADIOS - RADIOS) + RADIOS);
                } else {
                    // Generates a random integer within the range of MAX_SPEED
                    args[i] = Integer.toString(rand.nextInt(MAX_SPEED));
                }
            }
        }
    }

    /**
     * The main method is responsible for running the animation by creating
     * a ball object and passing it to the drawAnimation method.
     *
     * @param args the command line arguments used to set the initial
     *             position and velocity of the ball. The first two arguments
     *             should be the x and y coordinates of the initial position
     *             of the ball. and two arguments after that is velocity.
     */
    public static void main(String[] args) {
        BouncingBallAnimation m = new BouncingBallAnimation();
        if (args.length < EXPECTED_SIZE_FOR_ARRAY_ARGS) {
            args = m.fillArrayArgsWithNecessaryData(args);
        }
        m.dataIntegrityCheck(args);
        Point p1 = new Point(Double.parseDouble(args[0]),
                Double.parseDouble(args[1]));
        m.drawAnimation(p1, Double.parseDouble(args[2]),
                Double.parseDouble(args[3]));
    }
}