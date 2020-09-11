package gamesetting;

import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import java.util.List;

/**
 * Game flow class.
 * The class role is to run all the levels in "one run".
 */
public class GameFlow {

    private AnimationRunner runner;
    public Counter score;
    public Counter lives;
    private KeyboardSensor keyboard;

    /**
     * Construct a GameFlow object.
     * @param ar that represents the animation runner.
     * @param ks that represents the keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
        this.score = new Counter();
        this.lives = new Counter();
        lives.increase(3);
    }

    /**
     * The method role is to run all the levels in one run - one after one.
     * When the counter of lives shows 0 - it will break the loop.
     * @param levels that represents the levels that the game will show.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            // loading the level
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.lives, this.score);
            level.initialize();

            // while there are more bricks and lives - continue the run.
            while ((level.getRemainingBlocks().getValue() > 0) && (level.getLives().getValue() > 0)) {
                level.playOneTurn();
            }
            // while there is no more lives - exit.
            if (level.getLives().getValue() == 0) {
                break;
            }
        }
        // update the score
//        HighScores scr = new HighScores(this.score.getValue());
//        scr.UpdateScoreFile();
        // the player won the game - show the win board
        runner.run(new KeyPressStoppableAnimation(runner.getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                new EndScreen(runner.getKeyboardSensor(), this.score, 1)));
        runner.getGui().close();
    }
}
