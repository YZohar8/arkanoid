// 211970389 Yonatan Zohar
package Game.interfaces;

import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation in a game.
 * It defines methods for executing a single frame of the animation
 * and determining whether the animation should stop or continue.
 */
public interface Animation {

    /**
     * Perform a single frame of the animation.
     *
     * @param d the DrawSurface on which the frame will be drawn
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check whether the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
