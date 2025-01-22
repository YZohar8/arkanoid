// 211970389 Yonatan Zohar
package Game.animtion;

import Game.interfaces.Animation;
import Game.objects.PrintMessage;
import Game.objects.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;


/**
 * The CountdownAnimation class represents an animation that displays
 * a countdown.
 * It implements the Animation interface.
 */
public class CountdownAnimation implements Animation {
    //define magic number
    private static final int FONT = 50, WAIT_TIME_FOR_SECOND = 1000;
    // define fields
    private int number;
    private int timeWait;
    private SpriteCollection sprite;
    private boolean flag;

    /**
     * Constructs a CountdownAnimation object with the specified number,
     * duration in seconds, and sprite collection.
     *
     * @param number The starting number for the countdown
     * @param seconds The duration of the countdown in seconds
     * @param sprite The SpriteCollection containing the sprites to be
     *              drawn during the countdown
     */
    public CountdownAnimation(int number, int seconds,
                              SpriteCollection sprite) {
        this.number = number;
        this.timeWait = seconds * WAIT_TIME_FOR_SECOND / (number + 1);
        this.sprite = sprite;
        flag = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        PrintMessage printMessage = new PrintMessage(d.getWidth(),
                d.getHeight());
        printMessage.printInMiddleLine(Color.BLACK, d, this.number + "",
                FONT);
        this.sprite.drawAllOn(d);
        number--;
    }

    @Override
    public boolean shouldStop() {
        if (flag) {
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(this.timeWait);
        }
        flag = true;
        return (this.number == -1);

    }
}
