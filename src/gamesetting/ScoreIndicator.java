package gamesetting;
// 316554641

import biuoop.DrawSurface;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * ScoreIndicator class.
 * The class role is to represent the score of the game.
 */
public class ScoreIndicator implements Sprite {

    private Rectangle rectangle;
    private Counter counter;

    /**
     * Counter the score indicator object.
     * @param rectangle that represent the rectangle that we will create.
     * @param counter the counter of the score.
     */
    public ScoreIndicator(Rectangle rectangle, Counter counter) {
        this.rectangle = rectangle;
        this.counter = counter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        DrawSurface draw = d;
        // declaring the size of the game board
        double widthBoard = draw.getWidth();

        // creating the score board
        Color blockColor = new Color(0xC4D1CE);
        draw.setColor(blockColor);
        draw.fillRectangle(0, 0, (int) widthBoard, 20);
        // writing the content
        d.setColor(Color.black);
        d.drawText(((int) widthBoard / 2) - 20, 15, "Score: " + this.counter.getValue(), 16);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }
}
