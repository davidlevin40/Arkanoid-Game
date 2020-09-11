package levels;
// 316554641

import biuoop.DrawSurface;
import gamesetting.GameLevel;
import interfaces.Sprite;
import java.awt.Color;

/**
 * FourthLevelMap class.
 * The class role is to create the background of the forth level.
 */
public class FourthLevelMap implements Sprite {

    // height of 'screen'
    private static final int HEIGHT = 600;
    // width of 'screen'
    private static final int WIDTH = 800;
    // upgrade the visibility of the code --> similarly to WIDTH / 80
    private static final int WI_HEL = 10;
    // upgrade the visibility of the code --> similarly to WIDTH / 160
    private static final int WI_HELP = 5;

    @Override
    public void drawOn(DrawSurface d) {
        // draw the background
        d.setColor(new Color(0x2F7CC9));
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        // draw the first rain
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine((WIDTH / 5) + (i * 8), (WIDTH / 2), (WI_HEL * 13) + (i * 8), HEIGHT);
        }
        // draw the second rain
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine((WIDTH / 2) + (WI_HEL * 22) + (i * 7), (WIDTH / 2) + (WI_HEL * 12),
                    HEIGHT + (i * 7), HEIGHT);
        }
        // draw the first cloud
        d.setColor(new Color(0xA8BDBA));
        d.fillCircle((WIDTH / 5) + WI_HELP, (WIDTH / 2), (WI_HEL * 2));
        d.setColor(new Color(0xA8BDBA));
        d.fillCircle((WIDTH / 4) - (WI_HEL * 2), (WIDTH / 2) + (WI_HEL * 2), (WI_HEL * 2));
        d.setColor(new Color(0x94AAA6));
        d.fillCircle((WIDTH / 4), (WIDTH / 2) - WI_HEL, (WI_HEL * 2));
        d.setColor(new Color(0x899E9B));
        d.fillCircle((WIDTH / 4) + (WI_HEL * 2) + WI_HELP, (WIDTH / 2), (WI_HEL * 3));
        d.setColor(new Color(0x899E9B));
        d.fillCircle((WIDTH / 4) + WI_HEL, (WIDTH / 2) + (WI_HEL * 2) + WI_HELP, WI_HEL + WI_HELP);
        // draw the second cloud
        d.setColor(new Color(0xA8BDBA));
        d.fillCircle((WIDTH / 2) + (WI_HEL * 22), (WIDTH / 2) + (WI_HEL * 11), (WI_HEL * 2));
        d.setColor(new Color(0xA8BDBA));
        d.fillCircle((WIDTH / 2) + (WI_HEL * 23), (WIDTH / 2) + (WI_HEL * 13), (WI_HEL * 2));
        d.setColor(new Color(0xA0B6B2));
        d.fillCircle((WIDTH / 2) + (WI_HEL * 24) + WI_HELP, (WIDTH / 2) + (WI_HEL * 11) + WI_HELP, (WI_HEL * 2));
        d.setColor(new Color(0x899E9B));
        d.fillCircle((WIDTH / 2) + (WI_HEL * 28), (WIDTH / 2) + (WI_HEL * 12), (WI_HEL * 2) + WI_HELP);
        d.setColor(new Color(0x899E9B));
        d.fillCircle((WIDTH / 2) + (WI_HEL * 25) + WI_HELP, (WIDTH / 2) + (WI_HEL * 13), WI_HEL + WI_HELP);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
