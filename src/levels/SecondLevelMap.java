package levels;

import biuoop.DrawSurface;
import gamesetting.GameLevel;
import interfaces.Sprite;
import java.awt.Color;

/**
 * SecondLevelMap class.
 * The class role is to create the background of the second level.
 */
public class SecondLevelMap implements Sprite {

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
        d.setColor(new Color(0xD7E7D2));
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        // creating the lines from the sunshine
        for (int i = 0; i < (WI_HEL * 70); i += 5) {
            d.setColor(new Color(0xD4CE93));
            d.drawLine((WI_HEL * 14), (WI_HEL * 14), i, (WI_HEL * 25));
        }
        // draw the sunshine
        d.setColor(new Color(0xFFDCDCA8, true));
        d.fillCircle((WI_HEL * 14), (WI_HEL * 14), (WI_HEL * 5) + WI_HELP);
        d.setColor(new Color(0xEFC616));
        d.fillCircle((WI_HEL * 14), (WI_HEL * 14), (WI_HEL * 4) + WI_HELP);
        d.setColor(new Color(0xDECC1B));
        d.fillCircle((WI_HEL * 14), (WI_HEL * 14), (WI_HEL * 3) + WI_HELP);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
