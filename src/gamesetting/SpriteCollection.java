package gamesetting;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.Collection;

/**
 * SpriteCollection class.
 * A SpriteCollection representing the objects that can be drawn to the screen.
 */
public class SpriteCollection {
    private Collection<Sprite> sprites;

    /**
     * Construct a Sprite Collection- an array list of rectangles.
     *
     */
    public SpriteCollection() {
        sprites = new ArrayList<Sprite>();
    }

    /**
     * The function role is to add some object to the sprite collection.
     *
     * @param s a sprite collection of objects
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * The function to call timePassed() on all sprites.
     *
     */
    public void notifyAllTimePassed() {
        Collection<Sprite> sprite = new ArrayList<>(this.sprites);
        for (Sprite c : sprite) {
            c.timePassed();
        }
    }

    /**
     * Remove sprite from the collection.
     * @param s the sprite that we want to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * The function role is to call drawOn on all sprites with the given surface for all the sprites.
     *
     * @param d draw surface to draw the objects.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite c : this.sprites) {
            c.drawOn(d);
        }
    }
}
