// 211970389 Yonatan Zohar
package Game.interfaces;

import Game.objects.Block;
import geometric.objects.Ball;

/**
 * The HitListener interface represents an object that listens for hit events.
 * Implementing classes can define the behavior when an object is hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit  the block being hit
     * @param hitter    the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
