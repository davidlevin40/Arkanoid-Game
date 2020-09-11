package gamesetting;
// 316554641

import geometry.Ball;
import geometry.Block;
import interfaces.HitListener;

/**
 * ScoreTrackingListener class. Give us the information of an hit listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Counter for Score Tracking Listener.
     * @param scoreCounter that represents the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}