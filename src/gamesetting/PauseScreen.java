package gamesetting;
// 316554641

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

import java.awt.Color;

/**
 * PauseScreen class.
 * The class role is to represent the pause screen.
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    // height of 'screen'
    private static final int HEIGHT = 600;
    // width of 'screen'
    private static final int WIDTH = 800;

    /**
     * Construct the Pause screen object.
     * @param k that represents the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw the surface
        Color blue = new Color(0x689FB3);
        d.setColor(blue);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        // draw the text
        d.setColor(Color.BLACK);
        d.drawText(170, d.getHeight() / 2, "paused - press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
