// 211970389 Yonatan Zohar
package geometric.test;

import biuoop.DrawSurface;
import biuoop.GUI;
import geometric.objects.Ball;
import geometric.objects.Point;
import geometric.objects.Velocity;
import global.Global;

import java.util.Random;
import java.awt.Color;

/**
 * A class that creates an animation of multiple balls bouncing around the
 * screen.
 */
public class MultipleBouncingBallsAnimation {
    // magic variables
    private static final int X_START = 0;
    private static final int Y_START = 0;
    private static final int SIZE = 200;
    private static final int NUM_OF_COLOR = 256;
    private static final int MAX_SPEED = 65;
    private static final int RADIOS_TO_FLAG = 50;
    private static final int MAX_RADIOS = 100;
    private static final String DEFAULT_RADIOS = "10";
    /**
     * Creates an array of geometric.Ball geometric.objects with the given sizes. and set the
     * borders of the window in which the ball is drawn
     *
     * @param args An array of Strings representing the sizes of the balls.
     * @return An array of geometric.Ball geometric.objects.
     */
    public Ball[] createBallArrayWithKnownSize(String[] args) {
        // Create a new geometric.Ball array with the same length as the args array.
        Ball[] array = new Ball[args.length];
        // for each cell in the array creates a ball
        for (int i = 0; i < array.length; i++) {
            Color randColour = this.createRandomColor();
            array[i] = new Ball(Point.createRandomPoint(X_START, Y_START,
                    SIZE, Integer.parseInt(args[i])),
                    Integer.parseInt(args[i]), randColour);
            // set the borders of the window in which the ball is drawn
            array[i].setWindowStart(X_START, Y_START);
            array[i].setWindowEnd(SIZE, SIZE);
        }
        return array;
    }

    /**
     * Generates a random color.
     *
     * @return A java.awt.Color object representing the generated color.
     */
    public java.awt.Color createRandomColor() {
        Random rand = new Random();
        // Generate random values for red, green, and blue.
        int red = rand.nextInt(NUM_OF_COLOR);
        int green = rand.nextInt(NUM_OF_COLOR);
        int blue = rand.nextInt(NUM_OF_COLOR);
        // Create and return a new Color object with the generated values.
        return new Color(red, green, blue);
    }

    /**
     * Sorts an array of geometric.Ball geometric.objects by size using bubble sort.
     *
     * @param array An array of geometric.Ball geometric.objects.
     */
    public void bubbleSortByRadius(Ball[] array) {
        boolean flag;
        // Loop through the array once for each element in the array.
        for (int i = 0; i < array.length; i++) {
            flag = true;
            /* Loop through the array again, comparing adjacent elements and
               swapping them if they are in the wrong order. */
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j].getSize() > array[j + 1].getSize()) {
                    Ball tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                    flag = false;
                }
            }
            /* If no swaps were made during the last pass through the array,
               the array is already sorted and we can break out of the
               loop early.    */
            if (flag) {
                break;
            }
        }
    }

    /**
     * Sets velocity to each ball in the given array.
     * The speed and the angle for the velocity is randomly.
     * The speed is also dependent on the ball size,
     * and bigger balls get slower speeds.
     *
     * @param array the array of balls to set velocity to
     */
    public void setVelocityToArray(Ball[] array) {
        Random rand = new Random();
        // define variables
        double speed = rand.nextDouble() * MAX_SPEED;
        double lastSpeed = speed;
        boolean flag = false;
        // lop om all the array
        for (Ball ball : array) {

            /* if the previous ball had a radius greater than 50 then the speeds
             can be equal otherwise the speed must be smaller     */
            if (flag) {
                speed = rand.nextDouble() * speed;
            } else {
                speed = rand.nextDouble() * speed;
                while (speed == lastSpeed) {
                    speed = rand.nextDouble() * speed;
                }
            }
            // define velocity
            Velocity v = Velocity.fromAngleAndSpeed(
                    rand.nextDouble() * Global.ANGLE, speed);
            ball.setVelocity(v);
            lastSpeed = speed;
            // if the radius is greater than 50, the "flag" lights up
            if (ball.getSize() >= RADIOS_TO_FLAG) {
                flag = true;
            }
        }
    }

    /**
     * Draws a ball animation on the screen.
     * The function repeatedly moves the balls in the given array
     * one step at a time and then draws them on a graphical user interface
     * (GUI) with a delay of 50 milliseconds between frames.
     *
     * @param array the array of balls to animate
     */
    public void drawManyAnimation(Ball[] array) {
        // create a GUI window
        GUI gui = new GUI("title", SIZE, SIZE);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // infinity lop
        while (true) {
            // move each ball one step at a time
            for (Ball ball : array) {
                ball.moveOneStep();
            }
            DrawSurface d = gui.getDrawSurface();
            // draw each ball on the drawing surface
            for (Ball ball : array) {
                ball.drawOn(d);
            }
            // show all the drawing surface
            gui.show(d);
            // pause for 50 milliseconds before the next frame
            sleeper.sleepFor(Global.TIME_WAIT);
        }
    }

    /**
     * Performs data integrity checks on an array of arguments.
     * If an argument can be parsed as an integer and is greater than 100,
     * or if an argument cannot be parsed as an integer, it is also replaced
     * with a random number.
     *
     * @param args The array of arguments to be checked and modified.
     */
    public void dataIntegrityCheck(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < args.length; i++) {
            try {
                // Attempt to parse the argument as an integer
                int tmp = Integer.parseInt(args[i]);
                if (tmp > MAX_RADIOS) {
                    /* If the parsed integer is greater than 100,
                     replace it with a random number */

                    System.out.println("The insertion of a radius greater than "
                            + MAX_RADIOS
                            + ", so it was replaced by a random number.");
                    args[i] = Integer.toString(rand.nextInt(
                            RADIOS_TO_FLAG) + 1);
                }
                if (tmp <= 0) {
                    System.out.println("The insertion of a radius smaller than"
                            + " 0, so it was replaced by a random number.");
                    args[i] = Integer.toString(rand.nextInt(
                            RADIOS_TO_FLAG) + 1);
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
     *This is the main function that runs the multiple bouncing balls animation.
     *
     * @param args an array of String arguments passed to the program
     */

    public static void main(String[] args) {
        // if args is empty create new array
        if (args.length == 0) {
            System.out.println("You didn't put anything in, so one default"
                    + " size ball was drawn.");
            args = new String[1];
            args[0] = DEFAULT_RADIOS;
        }
        // use with the function in the class geometric.geometricTest.MultipleBouncingBallsAnimation
        MultipleBouncingBallsAnimation m = new MultipleBouncingBallsAnimation();
        m.dataIntegrityCheck(args);
        Ball[] array = m.createBallArrayWithKnownSize(args);
        m.bubbleSortByRadius(array);
        m.setVelocityToArray(array);
        m.drawManyAnimation(array);
    }
}


