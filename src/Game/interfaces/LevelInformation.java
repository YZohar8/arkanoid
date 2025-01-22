// 211970389 Yonatan Zohar
package Game.interfaces;

import Game.animtion.GameLevel;
import Game.objects.Block;

import geometric.objects.Velocity;

import java.util.List;

/**
 * The LevelInformation interface represents the information of a game level.
 * It provides methods to retrieve various attributes and settings of a level.
 */
public interface LevelInformation {
    /**
     * Get the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Get the velocities of the balls in the level.
     *
     * @return a list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Get the speed of the paddle.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Get the width of the paddle.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Get the name of the level.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Get the background sprite of the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Get the blocks that show up in this level.
     *
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Get the number of blocks that should be removed.
     *
     * @return the number of blocks to be removed
     */
    int numberOfBlocksToRemove();

    /**
     * Initialize the level with the specified game instance.
     * This method is responsible for adding the blocks and other components
     * to the game.
     *
     * @param game the GameLevel instance to initialize
     */
    void initialize(GameLevel game);
}
