// 211970389 Yonatan Zohar
package Game.objects;

import Game.Listener.BallRemover;
import Game.Listener.ScoreTrackingListener;
import Game.interfaces.Sprite;
import biuoop.DrawSurface;
import geometric.objects.Point;

/**
 * The ScoreIndicator class represents a sprite that displays the current score.
 */
public class ScoreIndicator implements Sprite {
    // define magic variables
    private static final int FONT = 20, WIDTH = 100;
    private static final int SPACE = 2;

    // define fields
    private Block shape;
    private ScoreTrackingListener score;
    private int x;
    private int y;
    private BallRemover ballRemover;
    private String name;

    /**
     * Constructs a ScoreIndicator object with the specified position,
     * dimensions, color, and score tracker.
     *
     * @param upperLeft    the upper left corner of the score indicator
     * @param width        the width of the score indicator
     * @param height       the height of the score indicator
     * @param ballRemover  the ball remover listener
     * @param color        the color of the score indicator
     * @param score        the score tracking listener to display the score
     * @param name         the name of the score indicator
     */
    public ScoreIndicator(Point upperLeft, double width, double height,
                          BallRemover ballRemover, java.awt.Color color,
                          ScoreTrackingListener score, String name) {
        this.x = (int) ((width / 2) + upperLeft.getX());
        this.y = (int) ((height) + upperLeft.getY() - SPACE);
        this.shape = new Block(upperLeft, width, height, color);
        this.score = score;
        this.ballRemover = ballRemover;
        this.name = name;
    }

    /**
     * Draws the score indicator on the given draw surface.
     *
     * @param d the draw surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.shape.drawOn(d);
        d.drawText(0, y, "num balls: " + this.ballRemover.getValue(), FONT);
        d.drawText(x, y, "score: " + score.getValue(), FONT);
        d.drawText(d.getWidth() - WIDTH, y, this.name, FONT);
    }

    @Override
    public void timePassed() {
        // do nothing
    }
}
