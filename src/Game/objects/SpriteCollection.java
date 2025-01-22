// 211970389 Yonatan Zohar
package Game.objects;

import Game.interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * A collection of sprites.
 * Responsible for adding and updating all sprites in the game.
 */
public class SpriteCollection {
    // define fields
    private java.util.List<Sprite> listSprite;

    /**
     * Constructor - creates an empty SpriteCollection.
     */
    public SpriteCollection() {
        listSprite = new ArrayList<>();
    }

    /**
     * Adds the given sprite to this collection.
     *
     * @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.listSprite.add(s);
    }

    /**
     * this method Calls timePassed() on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        try {
            for (Sprite sprite : listSprite) {
                sprite.timePassed();
            }
        } catch (ConcurrentModificationException e) {
            //it is ok
        }


    }

    /**
     * this method Calls drawOn(d) on all sprites in the collection.
     *
     * @param d the DrawSurface on which to draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : listSprite) {
            sprite.drawOn(d);
        }
    }

    /**
     * the function receive Spring object and remove it from the list.
     * @param s Sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.listSprite.remove(s);
    }
}

