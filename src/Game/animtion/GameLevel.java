// 211970389 Yonatan Zohar
package Game.animtion;

import Game.Listener.BallRemover;
import Game.Listener.BlockRemover;
import Game.Listener.ScoreTrackingListener;
import Game.interfaces.Animation;
import Game.interfaces.LevelInformation;
import Game.interfaces.Sprite;
import Game.interfaces.Collidable;
import Game.logic.AnimationRunner;
import Game.objects.Block;
import Game.objects.Counter;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometric.objects.Ball;
import geometric.objects.Point;
import geometric.objects.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Game.objects.SpriteCollection;
import Game.objects.GameEnvironment;
import Game.objects.ScoreIndicator;


/**
 * This class represents the main game class that runs the game,
 * initializes the game environment,and manages the game objects.
 */
public class GameLevel implements Animation {
    // define magic variables
    /**
     * The thickness of the rectangles that are in the border of the screen.
     */
    public static final int BOARD_SIZE = 25;
    // private magic variables
    private static final int WIDTH = 800, HEIGHT = 600, BALL_SIZE = 7,
            X_START = 0, Y_START = 0,
            BALL_SPEED = 4, FONT2 = 25, FONT1 = 40,
            SCORE_INDICATOR_SIZE = 20;
    private static final Point BALL_START_CENTER = new Point(395, 540);

    //define fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI boardGame;
    private Color boardColor;
    private java.util.List<Ball> balls;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener score;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * Constructs a new GameLevel object with the specified level.
     *
     * @param levelInformation The level information of the game level
     * @param gui The GUI object for the game level
     * @param ar The animation runner for the game level
     * @param ak The keyboard sensor for the game level
     * @param score The score tracking listener for the game level
     */
    public GameLevel(LevelInformation levelInformation, GUI gui,
                     AnimationRunner ar, KeyboardSensor ak,
                     ScoreTrackingListener score) {
        // reset the list
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.balls = new ArrayList<>();
        // add listener
        Counter blockCounter = new Counter();
        this.blockRemover = new BlockRemover(this, blockCounter);
        Counter ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, ballCounter);
        // reset the score
        this.score = score;
        // create a GUI window
        this.boardGame = gui;
        // rest the animation runner
        this.runner = ar;
        // reset keyboard sensor
        this.keyboard = ak;
        // reset level game
        this.levelInformation = levelInformation;
    }

    /**
     * Gets the GUI window of the game.
     *
     * @return the GUI window of the game
     */
    public GUI getBoardGame() {
        return this.boardGame;
    }

    /**
     * Gets the list of balls in the game.
     *
     * @return the list of balls in the game
     */
    public List<Ball> getBalls() {
        return balls;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the SpriteCollection.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes the game by creating the paddle, blocks, and ball objects,
     * and adding them to the game.
     */
    public void initialize() {
        // Creates blocks at the edges of the screen
        this.creatingBlocksForTheScreenFrame();
        // creating Block that "kill" the ball if them impact
        Block down = new Block(new geometric.objects.Point(0,
                HEIGHT), WIDTH, BOARD_SIZE, Color.pink);
        down.addHitListener(this.ballRemover);
        this.addSprite(down);
        this.addCollidable(down);
        // update the level
        this.levelInformation.initialize(this);

    }

    /**
     * Creates the blocks for the screen frame and adds them to the game.
     * The blocks are used as borders and the ball should not pass them.
     */
    private void creatingBlocksForTheScreenFrame() {
        // create the block objects for each side
        Block up, left, right;
        up = new Block(new geometric.objects.Point(0, SCORE_INDICATOR_SIZE),
                WIDTH, BOARD_SIZE, Color.black);
        left = new Block(new geometric.objects.Point(0, SCORE_INDICATOR_SIZE),
                BOARD_SIZE, HEIGHT, Color.black);
        right = new Block(new geometric.objects.Point(WIDTH
                - BOARD_SIZE, SCORE_INDICATOR_SIZE),
                BOARD_SIZE, HEIGHT, Color.black);
        // add the blocks to the game as sprites and collidables
        this.addSprite(up);
        this.addSprite(left);
        this.addSprite(right);
        this.addCollidable(up);
        this.addCollidable(left);
        this.addCollidable(right);
    }

    /**
     * Creates a ball object with the given angle and color,
     * and adds it to the game.
     *
     * @param angle the angle the ball should move in
     * @param color the color of the ball
     * @return Ball - the new ball that create;
     */
    public Ball creatingBall(int angle, Color color) {
        // create a new ball object with the given parameters
        Ball newBall = new Ball(BALL_START_CENTER, BALL_SIZE,
                color, this.environment);
        // set the boundaries for the ball's movement
        newBall.setWindowStart(X_START + BOARD_SIZE,
                Y_START + BOARD_SIZE);
        newBall.setWindowEnd(WIDTH - BOARD_SIZE, HEIGHT - BOARD_SIZE);
        // set the ball's velocity based on the given angle and a default speed
        Velocity v = Velocity.fromAngleAndSpeed(angle, BALL_SPEED);
        newBall.setVelocity(v);
        // add the ball to the game as a sprite and to the list of balls
        this.addSprite(newBall);
        this.balls.add(newBall);
        this.ballRemover.addCounter(1);
        return newBall;
    }

    /**
     * Creates a block object with the given parameters,
     * and adds it to the game as a sprite and collidable.
     *
     * @param point       the top-left corner of the block
     * @param blockWidth  the width of the block
     * @param blockHeight the height of the block
     * @param color       the color of the block
     * @return Block - the new block that create.
     */
    public Block creatingBlock(geometric.objects.Point point, int blockWidth,
                               int blockHeight, Color color) {
        // create a new block object with the given parameters
        Block newBlock = new Block(point, blockWidth, blockHeight, color);
        // add the block to the game as a sprite and collidable
        this.addCollidable(newBlock);
        this.addSprite(newBlock);
        newBlock.addHitListener(this.blockRemover);
        newBlock.addHitListener(this.score);
        blockRemover.addCounter(1);
        return newBlock;
    }


    /**
     * Starts the animation loop for the game.
     * This method runs continuously until the game is closed.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(3, 2, this.sprites));
        this.running = true;
        this.runner.run(this);

    }

    /**
     * Removes the specified collidable object from the game's environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollision(c);
    }

    /**
     * Removes the specified sprite object from the game's sprite collection.
     *
     * @param s the sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * that method remove ball from the game.
     *
     * @param remove the ball that need remove
     */
    public void removeBall(Ball remove) {
        this.removeSprite(remove);
        this.balls.remove(remove);
    }

    private void conditionToStopRun() {
        // condition to stop the game - if win
        if (this.blockRemover.getValue() == 0) {
            this.score.removeAllBlocks();
            this.running = false;

        }
        // condition to stop the game - if lose
        if (this.ballRemover.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return (!this.running);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // check if the player want pause
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation animation = new
                    KeyPressStoppableAnimation(this.boardGame.getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, new PauseScreen());
            this.runner.run(animation);
        }
        // fill the board with the current board color
        d.setColor(boardColor);
        d.fillRectangle(X_START, Y_START, WIDTH, HEIGHT);
        // draw all the sprites on the board
        this.sprites.drawAllOn(d);
        // move all the object one move
        this.sprites.notifyAllTimePassed();
        // check if is need to stop
        conditionToStopRun();
    }

    /**
     * the method over pass an all balls and blocks and fix the location
     * the ball if the ball is contained in block.
     */
    public void fixTheBallsMove() {
        for (int i = 0; i < this.environment.sizeOfCollision(); i++) {
            for (Ball ball : this.balls) {
                Point ballCenter = new Point(ball.getX(), ball.getY());
                if (this.environment.getCollision(i).getCollisionRectangle()
                        .pointContained(ballCenter)) {
                    // fixing the ball move
                    Collidable c = this.environment.getCollision(i);
                    Point endPoint = c.getCollisionRectangle().
                            closePointOnWallsToPointContained(ballCenter);
                    Velocity v = c.hit(endPoint, ball.getVelocity());
                    ball.setVelocity(v);
                }
            }
        }
    }

    /**
     * Gets the game environment of the level.
     *
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Returns the number of blocks to remove in the level.
     *
     * @return the number of blocks to remove
     */
    public int howMuchBlocksToRemove() {
        return this.blockRemover.getValue();
    }

    /**
     * Returns the number of balls to remove in the level.
     *
     * @return the number of balls to remove
     */
    public int howMuchBallsToRemove() {
        return this.ballRemover.getValue();
    }

    /**
     * Creates a score indicator with the specified name.
     *
     * @param name The name to be displayed on the score indicator
     */
    public void crateIndicatorScore(String name) {
        // Creating score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Point(0, 0),
                WIDTH, SCORE_INDICATOR_SIZE, this.ballRemover, Color.lightGray,
                this.score, name);
        this.sprites.addSprite(scoreIndicator);
    }

    /**
     * Sets the color of the game board.
     *
     * @param color The color to set for the game board
     */
    public void setBoardColor(Color color) {
        this.boardColor = color;
    }

    /**
     * Gets the name of the level.
     *
     * @return the name of the level
     */
    public String getNameLevel() {
        return this.levelInformation.levelName();
    }

}