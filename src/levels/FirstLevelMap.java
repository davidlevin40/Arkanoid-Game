package levels;

import biuoop.DrawSurface;
import gamesetting.GameLevel;
import interfaces.Sprite;
import java.awt.Color;

/**
 * FirstLevelMap class.
 * The class role is to create the background of the first level.
 */
public class FirstLevelMap implements Sprite {

    // height of 'screen'
    private static final int HEIGHT = 600;
    // width of 'screen'
    private static final int WIDTH = 800;
    // location of the circle coordination
    private static final int CIRCLE = 135;

    @Override
    public void drawOn(DrawSurface d) {
        // draw the background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        // draw the blue lines
        Color blue = new Color(0x1128BF);
        d.setColor(blue);
        // right line
        d.drawLine(WIDTH / 2, CIRCLE, (WIDTH / 2) + (HEIGHT / 60) * 11, CIRCLE);
        // left line
        d.drawLine((CIRCLE * 2) + (WIDTH / 80), CIRCLE, WIDTH / 2, CIRCLE);
        // upper line
        d.drawLine(WIDTH / 2, WIDTH / 40, WIDTH / 2, HEIGHT / 5);
        // down line
        d.drawLine(WIDTH / 2, HEIGHT / 4, WIDTH / 2, (WIDTH / 2) - (HEIGHT / 4));
        // first circle
        d.drawCircle(WIDTH / 2, CIRCLE, WIDTH / 20);
        // second circle
        d.drawCircle(WIDTH / 2, CIRCLE, HEIGHT / 10);
        // third circle
        d.drawCircle(WIDTH / 2, CIRCLE, WIDTH / 10);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
