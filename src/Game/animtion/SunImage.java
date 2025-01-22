// 211970389 Yonatan Zohar
package Game.animtion;

import Game.interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The SunImage class represents a sprite that draws a sun on the screen.
 */
public class SunImage implements Sprite {
    private static final Color SUN_COLOR = Color.ORANGE;
    private static final int RAY_COUNT = 20;

    /**
     * Constructs a SunImage object.
     */
    public SunImage() {

    }

    /**
     * Paints the sun on the given draw surface.
     *
     * @param d the draw surface to draw on
     */
    private void paintTheSun(DrawSurface d) {
        int width = d.getWidth();
        int height = d.getHeight();
        int centerX = width / 4;
        int centerY = height / 4;
        int rayLength = Math.min(width, height) / 4;
        int sunRadius = Math.min(width, height) / 8;

        // Draw the sun body (big circle)
        d.setColor(SUN_COLOR);
        d.fillCircle(centerX, centerY, sunRadius);

        // Draw the sun rays (lines)
        for (int i = 0; i < RAY_COUNT; i++) {
            double angle = 2 * Math.PI * i / RAY_COUNT;
            int endX = (int) (centerX + rayLength * Math.cos(angle));
            int endY = (int) (centerY + rayLength * Math.sin(angle));

            d.setColor(SUN_COLOR);
            d.drawLine(centerX, centerY, endX, endY);
        }
    }

    /**
     * Draws the sun on the given draw surface.
     *
     * @param d the draw surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.paintTheSun(d);
    }

    /**
     * Updates the state of the sun over time.
     */
    @Override
    public void timePassed() {
        // do nothing
    }
}
