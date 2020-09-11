package interfaces;
// ID: 316554641

import biuoop.DrawSurface;
import gamesetting.GameLevel;

/**
 * Sprite interface.
 * A Sprite interface representing the game objects that can be drawn to the screen.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface for the game
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add objects to the game.
     *
     * @param g the game
     */
    void addToGame(GameLevel g);
}