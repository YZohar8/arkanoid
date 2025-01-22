// 211970389 Yonatan Zohar

import Game.interfaces.LevelInformation;
import Game.level.DirectHit;
import Game.level.Green3;
import Game.level.WideEasy;
import Game.logic.AnimationRunner;
import Game.objects.GameFlow;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the main class for running the "Block Breaker" game.
 */
public class Ass6Game {
    //define magic variables
    private static final int WIDTH = 800, HEIGHT = 600,
            FRAMES_PER_SECOND = 1000 / 60;
    private static final String NAME = "Arkanoid";

    /**
     * The main method for running the game.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI(NAME, WIDTH, HEIGHT);
        LevelInformation[] arrayLevels = {new DirectHit(), new WideEasy(),
                new Green3()};
        List<LevelInformation> levels = new ArrayList<>();
        // add levels to list
        for (String s : args) {
            try {
                int tmp = Integer.parseInt(s);
                if (tmp <= arrayLevels.length) {
                    levels.add(arrayLevels[tmp - 1]);
                }
            } catch (Exception e) {
                // do nothing
            }
        }
        // if is no levels
        if (levels.size() == 0) {
            levels.add(arrayLevels[1]);
            System.out.println("You didn't insert any steps or the steps you"
                    + " inserted don't exist.\nTherefore you have been put in"
                    + " a default stage in the game");
        }
        // reset the game
        GameFlow game = new GameFlow(new AnimationRunner(gui, FRAMES_PER_SECOND),
                gui.getKeyboardSensor(), gui);
        game.runLevels(levels);
    }
}

