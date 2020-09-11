package gamesetting;

import geometry.Ball;
import geometry.Block;
import interfaces.HitListener;

/**
 * Ball remover class.
 * The class role is to create an object that can remove balls.
 * Moreover, it will update the number of balls - for creating a better game.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Construct a ball remover.
     * @param gameLevel that represents the game that we want to update.
     * @param remainingBalls that represents the number of balls that are in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * The function role is to change some details when the ball get out of the screen: the counter, the sprite
     * collection and the game.
     * @param beingHit that represents the block - "dead zone".
     * @param hitter that represents the ball that we want to remove.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // decrease 1 from the counter of balls
        this.remainingBalls.decrease(1);
        this.gameLevel.removeSprite(hitter);
        hitter.removeFromGame(this.gameLevel);
    }
}
