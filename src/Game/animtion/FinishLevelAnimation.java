// 211970389 Yonatan Zohar
package Game.animtion;

import Game.interfaces.Animation;
import Game.objects.PrintMessage;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The FinishLevelAnimation class represents an animation displayed
 * when a level is finished.
 * It implements the Animation interface.
 */
public class FinishLevelAnimation implements Animation {
    //define magic number
    private static final int FONT = 50, WAIT_TIME = 1500;
    // define fields
    private String name;
    private boolean flag;

    /**
     * Constructs a FinishLevelAnimation object with the specified level name.
     *
     * @param name The name of the finished level
     */

    public FinishLevelAnimation(String name) {
        this.name = name;
        this.flag = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        PrintMessage printMessage = new PrintMessage(d.getWidth(),
                d.getHeight());
        d.setColor(Color.GRAY);
        printMessage.printInMiddleLine(Color.BLACK, d, "you finish"
                + " the level - " + this.name, FONT);
    }

    @Override
    public boolean shouldStop() {
        if (flag) {
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(WAIT_TIME);
            return true;
        }
        flag = true;
        return false;
    }
}
