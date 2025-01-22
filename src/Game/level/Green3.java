// 211970389 Yonatan Zohar
package Game.level;

import Game.animtion.GameLevel;
import Game.animtion.SunImage;
import Game.interfaces.LevelInformation;
import Game.interfaces.Sprite;
import Game.objects.Block;
import Game.objects.Paddle;
import geometric.objects.Ball;
import geometric.objects.Point;
import geometric.objects.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The "Green3" class represents a level in the game.
 * It implements the LevelInformation interface.
 */
public class Green3 implements LevelInformation {
    // define magic variables
    private static final Color DEFULT_BOARD_COLOR = Color.cyan,
            COLOR_TO_SECOND_BALL = Color.black;
    private static final Color[] ARRAY_COLORS_FOR_ROWS = {Color.gray,
            Color.magenta, Color.red, Color.yellow, Color.PINK, Color.green};
    private static final int BLOCK_WIDTH = 52, BLOCK_HEIGHT = 20, WIDTH = 800,
            HEIGHT = 600, SPACE = 100, PADDLE_SPEED = 6, PADDLE_WIDTH = 120,
            PADDLE_HEIGHT = 5, HOW_MUCH_ROWS = 6, MAX_BLOCKS_IN_ROW = 12,
             ANGLE_TO_THIRD_BALL = 7;
    private static final String NAME_LEVEL = "Green3";
    private static final Point PADDLE_START_CENTER = new Point(((WIDTH / 2)
            - (PADDLE_WIDTH / 2)), HEIGHT - (40 + PADDLE_HEIGHT));

    // define fields
    private java.util.List<Ball> balls;
    private List<Block> blocks;
    private Sprite backRound;

    /**
     * Constructs a new instance of the Green3 class.
     */
    public Green3() {
        this.balls = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.backRound = new SunImage();
    }

    @Override
    public int numberOfBalls() {
        return balls.size();
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (Ball ball : this.balls) {
            velocities.add(ball.getVelocity());
        }
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }
    @Override
    public String levelName() {
        return NAME_LEVEL;
    }
    @Override
    public Sprite getBackground() {
        return this.backRound;
    }
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }
    @Override
    public void initialize(GameLevel game) {
        // add backRound
        game.addSprite(this.getBackground());
        // creating paddle
        Paddle paddle = new Paddle(PADDLE_START_CENTER, PADDLE_WIDTH,
                PADDLE_HEIGHT, PADDLE_SPEED, Color.ORANGE,
                GameLevel.BOARD_SIZE, WIDTH - GameLevel.BOARD_SIZE);
        paddle.addToGame(game);
        // creating ball
        balls.add(game.creatingBall(0, Color.black));
        balls.add(game.creatingBall(ANGLE_TO_THIRD_BALL, Color.black));

        // creating score indicator
        game.crateIndicatorScore(NAME_LEVEL);
        // crating blocks
        this.addBlocks(game);
        // set color
        game.setBoardColor(DEFULT_BOARD_COLOR);

    }

    /**
     * Adds blocks to the game board.
     *
     * @param game the Game object to add blocks to
     */
    private void addBlocks(GameLevel game) {
        int maxBlocksInRow = MAX_BLOCKS_IN_ROW;
        // Loop through each row
        for (int i = 0; i < HOW_MUCH_ROWS; i++) {
            Point p;
            double x, y;
            // Loop through each block in the row
            for (int j = 0; j < maxBlocksInRow; j++) {
                // Calculate the coordinates of the block
                x = WIDTH - GameLevel.BOARD_SIZE - ((j + 1) * (BLOCK_WIDTH));
                y = SPACE + (i * BLOCK_HEIGHT);
                p = new Point(x, y);
                game.creatingBlock(p, BLOCK_WIDTH, BLOCK_HEIGHT,
                        ARRAY_COLORS_FOR_ROWS[i]);
            }
            // Decrement the number of blocks in the row for the next iteration
            maxBlocksInRow--;
        }
    }
}
