package gamesetting;
// 316554641

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

import java.awt.Color;

/**
 * LoseScreen class.
 * The class role is to presents the end screen during the game.
 */
public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private int flag;
    // height of 'screen'
    private static final int HEIGHT = 600;
    // width of 'screen'
    private static final int WIDTH = 800;

    /**
     * Construct the EndScreen object.
     * @param k that represents the keyboard sensor.
     * @param flag that help us to represent the game statues.
     * @param score that represents the score.
     */
    public EndScreen(KeyboardSensor k, Counter score, int flag) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
        this.flag = flag;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw the surface
        Color blue = new Color(0x689FB3);
        d.setColor(blue);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);

        // if
        if (this.flag == 0) {
            d.setColor(Color.BLACK);
            d.drawText(185, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
        }
        if (this.flag == 1) {
            d.setColor(Color.BLACK);
            d.drawText(185, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

