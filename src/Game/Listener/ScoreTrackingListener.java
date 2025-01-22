// 211970389 Yonatan Zohar
package Game.Listener;

import Game.interfaces.HitListener;
import Game.objects.Block;
import Game.objects.Counter;
import geometric.objects.Ball;

/**
 * The ScoreTrackingListener class is responsible for tracking the score by
 * counting the number of block hits.
 */
public class ScoreTrackingListener implements HitListener {
    // define magic variables
    private static final int REMOVE_BLOCK = 5;
    private static final int FINISH = 100;

    // define fields
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener object with the specified
     * score counter.
     *
     * @param scoreCounter the counter to keep track of the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Handles the hit event by increasing the score when a block is hit.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(REMOVE_BLOCK);
    }

    /**
     * Increases the score by a predefined amount when all blocks are removed.
     */
    public void removeAllBlocks() {
        this.currentScore.increase(FINISH);
    }

    /**
     * Returns the current score.
     *
     * @return the current score
     */
    public int getValue() {
        return this.currentScore.getValue();
    }
}
