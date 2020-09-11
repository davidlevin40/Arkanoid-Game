package gamesetting;
// ID: 316554641

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * AnimationRunner class.
 * The class role is to run the animation functionality during the game.
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    // height of 'screen'
    private static final int HEIGHT = 600;
    // width of 'screen'
    private static final int WIDTH = 800;

    /**
     * Construct an animation runner.
     */
    public AnimationRunner() {
        this.framesPerSecond = 60;
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);
    }

    /**
     * Get the gui.
     * @return gui.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * @return the keyboard.
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.gui.getKeyboardSensor();
    }

    /**
     * The run method role is to run the animation of the game.
     * @param animation that represents the kind of animation that we want to show.
     */
    public void run(Animation animation) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
