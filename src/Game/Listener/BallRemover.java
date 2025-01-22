// 211970389 Yonatan Zohar
package Game.Listener;

import Game.interfaces.HitListener;
import Game.objects.Block;
import Game.objects.Counter;
import Game.animtion.GameLevel;
import geometric.objects.Ball;

/**
 * The BallRemover class is responsible for removing balls from the
 * game and keeping track of the remaining balls.
 */
public class BallRemover implements HitListener {
    // define fields
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover object with the specified game and counter.
     *
     * @param game           the game from which to remove balls
     * @param removedBalls   the counter to keep track of the remaining balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Handles the hit event by removing the ball from the game and
     * decreasing the remaining ball count.
     *
     * @param beingHit  the block being hit
     * @param hitter    the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.removeBall(hitter);
        this.remainingBalls.decrease();
    }

    /**
     * Increases the remaining ball count by the specified number.
     *
     * @param number  the number to increase the remaining ball count by
     */
    public void addCounter(int number) {
        this.remainingBalls.increase(number);
    }

    /**
     * Returns the current remaining ball count.
     *
     * @return the current remaining ball count
     */
    public int getValue() {
        return this.remainingBalls.getValue();
    }
}
