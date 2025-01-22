// 211970389 Yonatan Zohar
package Game.logic;

import Game.interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The "AnimationRunner" class is responsible for running animations.
 * It handles the timing and display of frames.
 */
public class AnimationRunner {
    // define fields
    private GUI gui;
    private int timeToFrame;
    private Sleeper sleeper;

    /**
     * Constructs a new instance of the AnimationRunner class.
     *
     * @param gui             the GUI object to display animations on
     * @param framesPerSecond the number of frames per second for the animation
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.timeToFrame = framesPerSecond;
        sleeper = new Sleeper();
    }

    /**
     * Runs the given animation.
     *
     * @param animation the Animation object to run
     */
    public void run(Animation animation) {
        DrawSurface d;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = this.timeToFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }
}
