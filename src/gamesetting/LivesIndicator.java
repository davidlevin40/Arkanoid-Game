package gamesetting;
// 316554641

import biuoop.DrawSurface;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * Live indicator class.
 * The class role is to represents the counter of lives.
 */
public class LivesIndicator implements Sprite {

    private Rectangle rectangle;
    private Counter counter;
    private String levelName;

    /**
     * Counter the score indicator object.
     * @param rectangle that represent the rectangle that we will create.
     * @param counter the counter of the score.
     * @param str that represents the level name.
     */
    public LivesIndicator(Rectangle rectangle, Counter counter, String str) {
        this.rectangle = rectangle;
        this.counter = counter;
        this.levelName = str;
    }

    @Override
    public void drawOn(DrawSurface d) {
        DrawSurface draw = d;
        // declaring the size of the game board
        double widthBoard = draw.getWidth();
        double heightBoard = draw.getHeight();

        // creating the score board
        Color blockColor = new Color(0xC4D1CE);
        draw.setColor(blockColor);
        draw.fillRectangle(0, 0, 100, 20);
        // writing the content
        d.setColor(Color.black);
        d.drawText(((int) widthBoard / 2) - 300, 15, "Lives: " + this.counter.getValue(), 16);
        d.drawText(((int) widthBoard / 2) + 180, 15, "Level Name: " + this.levelName, 16);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }
}
