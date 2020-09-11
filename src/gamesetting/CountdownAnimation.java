package gamesetting;
// ID: 316554641

import biuoop.DrawSurface;
import interfaces.Animation;
import java.awt.Color;

/**
 * CountdownAnimation class.
 * The class role is to presents the count down screen during the game.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private double helper;
    // height of 'screen'
    private static final int HEIGHT = 600;
    // width of 'screen'
    private static final int WIDTH = 800;

    /**
     * Construct a CountdownAnimation object.
     * @param numOfSeconds the total seconds that the counting will take.
     * @param countFrom that represents the counting number.
     * @param gameScreen that represents the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.helper = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // display the counting on top of the game screen itself
        Color blue = new Color(0x0194ca);
        d.setColor(blue);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        this.gameScreen.drawAllOn(d);

        Color timeColor = new Color(0xC5BEDB);
        d.setColor(timeColor);

        // each number will appear on the screen for this time
        long timeAppearOnScreen = (long) (this.numOfSeconds / helper);

        // start the counting -- "3"
        if (this.countFrom == 3) {
            d.drawText((d.getWidth() / 2) - 25, 100, Integer.toString(countFrom), 50);
        }

        // while the counting is between "2" to "0"
        if (this.countFrom != 0 && this.countFrom != 3) {
            d.drawText((d.getWidth() / 2) - 25, 100, Integer.toString(countFrom), 50);
            sleeper.sleepFor(timeAppearOnScreen);
        }

        // finish the counting -- "go"
        if (this.countFrom == 0) {
            d.drawText((d.getWidth() / 2) - 25, 100, "Go!", 50);
            sleeper.sleepFor(timeAppearOnScreen);
        }

        if (this.countFrom < 0) {
            this.stop = true;
        }

        // decreasing the "count from" by 1
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
