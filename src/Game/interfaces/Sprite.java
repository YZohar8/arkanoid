// 211970389 Yonatan Zohar
package Game.interfaces;

import biuoop.DrawSurface;

/**
 * The Sprite interface represents game objects that can be drawn on the
 * screen and updated location over time.
 */
public interface Sprite {

    /**
     * Draws the object on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the object
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed,
     * allowing it to update its internal state as needed.
     */
    void timePassed();

}
