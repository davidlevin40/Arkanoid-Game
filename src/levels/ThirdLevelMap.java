package levels;

import biuoop.DrawSurface;
import gamesetting.GameLevel;
import interfaces.Sprite;
import java.awt.Color;

/**
 * ThirdLevelMap class.
 * The class role is to create the background of the third level.
 */
public class ThirdLevelMap implements Sprite {

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
        d.setColor(new Color(0x29800C));
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        // draw the building
        d.setColor(new Color(0x181B1C));
        d.fillRectangle((WI_HEL * 5), (WIDTH / 2) + (WI_HEL * 7) + WI_HELP, (WI_HEL * 8), (HEIGHT / 4));
        d.setColor(new Color(0xA5B8BB));
        d.fillRectangle((WI_HEL * 5) + WI_HELP, (WIDTH / 2) + (WI_HEL * 8),
                (WI_HEL * 7), (HEIGHT / 4) - WI_HELP);
        // create the horizon lines
        d.setColor(new Color(0x181B1C));
        d.fillRectangle((WI_HEL * 5) + WI_HELP, (WIDTH / 2) + (WI_HEL * 8), (WI_HEL * 7), WI_HELP);
        d.fillRectangle((WI_HEL * 5) + WI_HELP, (WIDTH / 2) + (WI_HEL * 11), (WI_HEL * 7), WI_HELP);
        d.fillRectangle((WI_HEL * 5) + WI_HELP, (WIDTH / 2) + (WI_HEL * 14), (WI_HEL * 7), WI_HELP);
        d.fillRectangle((WI_HEL * 5) + WI_HELP, (WIDTH / 2) + (WI_HEL * 17), (WI_HEL * 7), WI_HELP);
        // create the verticals lines
        d.fillRectangle((WI_HEL * 6) + WI_HELP, (WIDTH / 2) + (WI_HEL * 8), WI_HELP, (WI_HEL * 14) + WI_HELP);
        d.fillRectangle((WI_HEL * 8), ((WIDTH / 2) + (WI_HEL * 8)), WI_HELP, (HEIGHT / 4) - WI_HELP);
        d.fillRectangle((WI_HEL * 9) + WI_HELP, (WIDTH / 2) + (WI_HEL * 8), WI_HELP, (WI_HEL * 14) + WI_HELP);
        d.fillRectangle((WI_HEL * 11), (WIDTH / 2) + (WI_HEL * 8), WI_HELP, (HEIGHT / 4) - WI_HELP);
        // create the "tower"
        d.setColor(new Color(0x222628));
        d.fillRectangle((WI_HEL * 8), (WIDTH / 2) + (WI_HEL * 3), (WI_HEL * 2) + WI_HELP,
                (WI_HEL * 4) + WI_HELP);
        d.setColor(new Color(0x303538));
        d.fillRectangle((WI_HEL * 9), (WIDTH / 4) + (WI_HEL * 2), 7, (WIDTH / 4) + WI_HEL);
        // create the "light"
        d.setColor(new Color(0xA78B03));
        d.fillCircle((WI_HEL * 9) + ((WI_HELP / 5) * 2), (WIDTH / 4) + (WI_HEL * 2), WI_HEL);
        d.setColor(new Color(0xC72B2B));
        d.fillCircle((WI_HEL * 9) + ((WI_HELP / 5) * 2), (WIDTH / 4) + (WI_HEL * 2), (WI_HELP / 5) * 7);
        d.setColor(new Color(0xE3D6D6));
        d.fillCircle((WI_HEL * 9) + ((WI_HELP / 5) * 2), (WIDTH / 4) + (WI_HEL * 2), (WI_HELP / 5) * 3);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
