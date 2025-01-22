// 211970389 Yonatan Zohar
package geometric.test;
import biuoop.GUI;
import biuoop.DrawSurface;
import geometric.objects.Line;
import geometric.objects.Point;

import java.util.Random;
import java.awt.Color;

/**
 * This class generates and draws random abstract art using lines.
 */
public class AbstractArtDrawing {
    // Constants for the size of each line and the size of the drawing surface
    private static final int SIZE = 10;
    private static final int WIDE = 400;
    private static final int HIGH = 300;
    private static final int RADIUS_OF_POINT = 3;

    /**
     * Generates a random line within the bounds of the drawing surface.
     *
     * @return a geometric.Line object representing a randomly generated line.
     */
    public Line generateRandomLine() {
        Random random = new Random();
        double x1 = random.nextDouble() * WIDE;
        double y1 = random.nextDouble() * HIGH;
        double x2 = random.nextDouble() * WIDE;
        double y2 = random.nextDouble() * HIGH;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Creates an array of random lines.
     *
     * @param size the number of lines to generate.
     * @return an array of geometric.Line geometric.objects representing the randomly
     * generated lines.
     */
    public Line[] createRandomLineArray(int size) {
        Line[] array = new Line[size];
        for (int i = 0; i < size; i++) {
            array[i] = generateRandomLine();
        }
        return array;
    }

    /**
     * Paints the middle point of each line in the given array on the given
     * drawing surface as a blue circle.
     *
     * @param array the array of lines to paint middle points for
     * @param d     the drawing surface to paint on
     */
    public void paintMiddlePoint(Line[] array, DrawSurface d) {
        for (Line line : array) {
            d.setColor(Color.blue);
            d.fillCircle((int) line.middle().getX(),
                    (int) line.middle().getY(), RADIUS_OF_POINT);
        }
    }

    /**
     * Paints each line in the given array on the given drawing
     * surface as a black line.
     *
     * @param array the array of lines to paint
     * @param d     the drawing surface to paint on
     */
    public void paintLain(Line[] array, DrawSurface d) {
        for (Line line : array) {
            d.setColor(Color.black);
            d.drawLine((int) line.start().getX(), (int) line.start().getY(),
                    (int) line.end().getX(), (int) line.end().getY());
        }
    }

    /**
     * Paints the intersection points between all pairs of lines in the given
     * array on the given drawing surface as red circles.
     *
     * @param array the array of lines to find and paint intersection points for
     * @param d     the drawing surface to paint on
     */
    public void paintIntersectionPoint(Line[] array, DrawSurface d) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                Point p1 = array[i].intersectionWith(array[j]);
                // if have intersection point
                if (p1 != null) {
                    d.setColor(Color.RED);
                    d.fillCircle((int) p1.getX(), (int) p1.getY(),
                            RADIUS_OF_POINT);
                }
            }
        }
    }

    /**
     * The main method to generate and display abstract art using random lines.
     * It creates a GUI window, draws random lines, and displays their
     * intersection points and middle points.
     *
     * @param args The command line arguments, unused in this method.
     */
    public static void main(String[] args) {
        // Create a GUI window
        GUI gui = new GUI("Random Lines", WIDE, HIGH);
        DrawSurface d = gui.getDrawSurface();
        AbstractArtDrawing m = new AbstractArtDrawing();
        //create random line array
        Line[] array = m.createRandomLineArray(SIZE);
        // Draw the random lines, the middle point and the intersection point
        m.paintLain(array, d);
        m.paintMiddlePoint(array, d);
        m.paintIntersectionPoint(array, d);
        // show all drawing surface in the GUI window
        gui.show(d);
    }
}
