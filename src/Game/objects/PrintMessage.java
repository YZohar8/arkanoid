// 211970389 Yonatan Zohar
package Game.objects;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The PrintMessage class is responsible for printing messages on the screen.
 */
public class PrintMessage {
    // define magic number
    private static final int UP_FROM_DOWN = 100;
    // define fields
    private int x;
    private int y;

    /**
     * Constructs a new PrintMessage object with the specified position.
     *
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     */
    public PrintMessage(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Prints three lines of text in the middle of the screen.
     *
     * @param colorText the color of the text
     * @param d         the DrawSurface object used for drawing
     * @param s1        the first line of text
     * @param font1     the font size of the first line of text
     * @param s2        the second line of text
     * @param font2     the font size of the second and third lines of text
     * @param s3        the third line of text
     */
    public void printInMiddleThreeRow(Color colorText, DrawSurface d, String s1,
                                      int font1, String s2, int font2,
                                      String s3) {
        int width = Math.max(s1.length(), Math.max(s2.length(), s3.length()));
        int xm = (this.x / 2) - width - width - width;
        int ym = (this.y / 2) - (width / 2);
        d.setColor(colorText);
        d.drawText(xm, ym, s1, font1);
        d.drawText(xm, ym + font1, s2, font2);
        d.drawText(xm, ym + font1 + font2, s3, font2);
    }

    /**
     * Prints a single line of text in the middle of the screen.
     *
     * @param colorText the color of the text
     * @param d         the DrawSurface object used for drawing
     * @param s1        the line of text
     * @param font1     the font size of the line of text
     */
    public void printInMiddleLine(Color colorText, DrawSurface d, String s1,
                                  int font1) {
        int width = s1.length();
        int xm;
        if (width > 1) {
            xm = width + width;
        } else {
            xm = (this.x / 2) - width;
        }
        int ym = (this.y / 2) - (width / 2);
        d.setColor(colorText);
        d.drawText(xm, ym, s1, font1);
    }

    /**
     * Prints two lines of text in the middle of the screen.
     *
     * @param colorText the color of the text
     * @param d         the DrawSurface object used for drawing
     * @param s1        the first line of text
     * @param font1     the font size of the first line of text
     * @param s2        the second line of text
     * @param font2     the font size of the second line of text
     */
    public void printInMiddleTwoLine(Color colorText, DrawSurface d, String s1,
                                     int font1, String s2, int font2) {
        int width = Math.max(s1.length(), s2.length());
        int xm = (this.x / 2) - width - width - width;
        int ym = (this.y / 2) - (width / 2);
        d.setColor(colorText);
        d.drawText(xm, ym, s1, font1);
        d.drawText(xm - (s2.length() * 3), ym + font1, s2, font2);
    }

    /**
     * Prints a single line of text on the screen.
     *
     * @param colorText the color of the text
     * @param d         the DrawSurface object used for drawing
     * @param s1        the line of text
     * @param font1     the font size of the line of text
     */
    public void printInDownLine(Color colorText, DrawSurface d, String s1,
                                  int font1) {
        int width = s1.length();
        int xm;
        if (width > 1) {
            xm = width + width;
        } else {
            xm = (this.x / 2) - width;
        }
        int ym = d.getHeight() - UP_FROM_DOWN;
        d.setColor(colorText);
        d.drawText(xm, ym, s1, font1);
    }
}
