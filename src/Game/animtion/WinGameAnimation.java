// 211970389 Yonatan Zohar
package Game.animtion;

import Game.interfaces.Animation;
import Game.objects.PrintMessage;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The WinGameAnimation class represents an animation displayed when the game is
 * over and user win the game.
 * It implements the Animation interface.
 */
public class WinGameAnimation implements Animation {
    //define magic number
    private static final int FONT1 = 40, FONT2 = 30, MAX_GLIMMER = 40;
    // define fields
    private int score;
    private boolean isGlimmer;
    private int glimmer;

    /**
     * Constructs a WinGameAnimation object with the specified score.
     *
     * @param score The final score of the game
     */
    public WinGameAnimation(int score) {
        this.score = score;
        this.isGlimmer = true;
        this.glimmer = 0;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        PrintMessage print = new PrintMessage(d.getWidth(), d.getHeight());
        d.setColor(Color.GRAY);
        print.printInMiddleLine(Color.BLACK, d, "you won! the game. your"
                        + " score is: " + this.score, FONT1);
        // glimmer message
        if (this.isGlimmer) {
            print.printInDownLine(Color.GRAY, d, "To exit from scan press"
                    + " \"space\"", FONT2);
        }
        this.glimmer++;
        if ((this.glimmer % MAX_GLIMMER) == 0) {
            this.isGlimmer = !this.isGlimmer;
            this.glimmer = 0;
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}

