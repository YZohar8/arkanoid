// 211970389 Yonatan Zohar
package Game.Listener;

import Game.interfaces.HitListener;
import Game.objects.Block;
import Game.objects.Counter;
import Game.animtion.GameLevel;
import geometric.objects.Ball;

/**
 * The BlockRemover class is responsible for removing blocks from the game and
 * keeping track of the remaining blocks.
 */
public class BlockRemover implements HitListener {
    // define fields
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover object with the specified game and counter.
     *
     * @param game           the game from which to remove blocks
     * @param removedBlocks  the counter to keep track of the remaining blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Handles the hit event by removing the block from the game
     * and decreasing the remaining block count.
     *
     * @param beingHit  the block being hit
     * @param hitter    the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease();
    }

    /**
     * Increases the remaining block count by the specified number.
     *
     * @param number  the number to increase the remaining block count by
     */
    public void addCounter(int number) {
        this.remainingBlocks.increase(number);
    }

    /**
     * Returns the current remaining block count.
     *
     * @return the current remaining block count
     */
    public int getValue() {
        return this.remainingBlocks.getValue();
    }
}
