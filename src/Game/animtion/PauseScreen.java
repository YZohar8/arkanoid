// 211970389 Yonatan Zohar
package Game.animtion;

import Game.interfaces.Animation;
import Game.objects.PrintMessage;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The PauseScreen class represents an animation displayed when
 * the game is paused.
 * It implements the Animation interface.
 */
public class PauseScreen implements Animation {
    // define magic numbers
    private static final int FONT_PRIME = 50, FONT = 32;

    /**
     * Constructs a PauseScreen object.
     */
    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        PrintMessage p = new PrintMessage(d.getWidth(), d.getHeight());
        p.printInMiddleTwoLine(Color.BLACK, d, "paused", FONT_PRIME,
                " press space to continue", FONT);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
