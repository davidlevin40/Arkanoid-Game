package interfaces;

import geometry.Ball;
import geometry.Block;

/**
 * HitListener interface.
 * represents "hit listener" that "starting to work" when the beingHit object is hit.
 */

public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit that represents the block that was hit.
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
