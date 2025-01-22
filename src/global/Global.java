// 211970389 Yonatan Zohar
package global;

import java.awt.Color;
import java.util.Random;

/**
 * This class contains global constants used in the application.
 */
public class Global {
    //define magic variables
    private static final int NUM_OF_COLOR = 256;

    /**
     * A small value used as an epsilon for floating point comparison.
     */
    public static final double EPSILON = Math.pow(10, -5);
    /**
     * A small distance whose addition will create a point that is not
     * equal to the previous point.
     */
    public static final double IS_NOT_ON_LINE = 5 * EPSILON;


    /**
     * The default time to wait in milliseconds.
     */
    public static final int TIME_WAIT = 50;

    /**
     * The default angle value in degrees.
     */
    public static final int ANGLE = 360;

    /**
     * Generates a random color.
     *
     * @return A java.awt.Color object representing the generated color.
     */
    public static java.awt.Color createRandomColor() {
        Random rand = new Random();
        // Generate random values for red, green, and blue.
        int red = rand.nextInt(NUM_OF_COLOR);
        int green = rand.nextInt(NUM_OF_COLOR);
        int blue = rand.nextInt(NUM_OF_COLOR);
        // Create and return a new Color object with the generated values.
        return new Color(red, green, blue);
    }
}
