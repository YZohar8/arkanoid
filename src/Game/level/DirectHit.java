// 211970389 Yonatan Zohar
package Game.level;

import Game.animtion.GameLevel;
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
import java.util.Random;


/**
 * The "DirectHit" class represents a level in the game.
 * It implements the LevelInformation interface.
 */
public class DirectHit implements LevelInformation {
    // define magic variables
    private static final Color DEFULT_BOARD_COLOR = Color.gray;
    private static final int PADDLE_WIDTH = 75, PADDLE_HEIGHT = 5, WIDTH = 800,
            HEIGHT = 600, PADDLE_SPEED = 6, BLOCK_WIDTH = 50,
            ANGLE_TO_RAND = 45, BLOCK_HEIGHT = 50;
    private static final Point PADDLE_START_CENTER = new Point(((WIDTH / 2)
            - (PADDLE_WIDTH / 2)), HEIGHT - (40 + PADDLE_HEIGHT));

    private static final String NAME_LEVEL = "Direct hit";

    // define fields
    private List<Ball> balls;
    private List<Block> blocks;

    /**
     * Constructs a new instance of the DirectHit class.
     * Initializes the balls and blocks lists.
     */
    public DirectHit() {
    this.balls = new ArrayList<>();
    this.blocks = new ArrayList<>();
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
        return null;
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
        // creating paddle
        Paddle paddle = new Paddle(PADDLE_START_CENTER, PADDLE_WIDTH,
                PADDLE_HEIGHT, PADDLE_SPEED, Color.ORANGE,
                 GameLevel.BOARD_SIZE, WIDTH - GameLevel.BOARD_SIZE);
        paddle.addToGame(game);
        // creating ball
        Random random = new Random();
        int angle = (random.nextInt(2 * ANGLE_TO_RAND) - ANGLE_TO_RAND);
        balls.add(game.creatingBall(angle, Color.white));
        // creating score indicator
        game.crateIndicatorScore(NAME_LEVEL);
        // crating blocks
        blocks.add(game.creatingBlock(new Point(((WIDTH / 2)
                - (BLOCK_WIDTH / 2)), BLOCK_HEIGHT), BLOCK_WIDTH, BLOCK_HEIGHT,
                Color.RED));
        // set color
        game.setBoardColor(DEFULT_BOARD_COLOR);
    }
}
