package gamesetting;
// 316554641

import geometry.Ball;
import geometry.Block;
import interfaces.HitListener;

/**
 * BlockRemover class.
 * In charge of removing blocks from the game, as well as keeping count.
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Construct a block remover object.
     * @param gameLevel that represents the game that we want to update.
     * @param remainingBlocks that represents the number of the remain blocks in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * The function role is tho take care that blocks that are hit should be removed from the game.
     * Moreover it will remove listener from the block.
     * @param beingHit that represents the block that was hit nad we want to update.
     * @param hitter that represents the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // decrease 1 from the counter of blocks
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        // update the ball that the block wsa remove
        hitter.getGameEnvironment().removeCollidable(beingHit);
    }
}