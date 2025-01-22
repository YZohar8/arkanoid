// 211970389 Yonatan Zohar
package Game.objects;

import Game.Listener.ScoreTrackingListener;
import Game.animtion.FinishLevelAnimation;
import Game.animtion.GameLevel;
import Game.animtion.KeyPressStoppableAnimation;
import Game.animtion.LoseGameAnimation;
import Game.animtion.WinGameAnimation;
import Game.interfaces.LevelInformation;
import Game.logic.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The GameFlow class is responsible for controlling the flow of the game,
 * including running the levels and managing the animations.
 */
public class GameFlow {
    // define fields
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private ScoreTrackingListener score;

    /**
     * Constructs a new GameFlow object with the specified animation runner,
     * keyboard sensor, and GUI.
     *
     * @param ar  the animation runner to run the game animations
     * @param ak  the keyboard sensor to listen for key presses
     * @param guy the GUI for the game
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ak, GUI guy) {
        this.animationRunner = ar;
        this.keyboardSensor = ak;
        this.gui = guy;
        // reset the score
        Counter score = new Counter();
        this.score = new ScoreTrackingListener(score);
    }

    /**
     * Runs the given list of levels.
     *
     * @param levels the list of level information
     */
    public void runLevels(List<LevelInformation> levels) {
        // go over on all levels
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.gui,
                    this.animationRunner, this.keyboardSensor, this.score);
            level.initialize();
            // As long as you haven't finished the stage or lost, keep running the stage
            while ((level.howMuchBlocksToRemove() > 0)
                    && (level.howMuchBallsToRemove() > 0)) {
                level.run();
            }
            // if you lose
            if (level.howMuchBallsToRemove() == 0) {
                KeyPressStoppableAnimation animation = new
                        KeyPressStoppableAnimation(this.gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY, new LoseGameAnimation(
                                this.score.getValue()));
                // show lose window
                this.animationRunner.run(animation);
                this.gui.close();
                return;
            } else {
                // show finish level window
                this.animationRunner.run(new FinishLevelAnimation(level.
                        getNameLevel()));
            }
        }
        // user finish all level so show winner window.
        KeyPressStoppableAnimation animation = new KeyPressStoppableAnimation(
                this.gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                new WinGameAnimation(this.score.getValue()));
        this.animationRunner.run(animation);
        this.gui.close();

    }
}
