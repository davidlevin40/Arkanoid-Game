package interfaces;

import geometry.Block;
import geometry.Velocity;
import java.util.List;

/**
 * LevelInformation interface.
 * The interface role is to add all the information that will represent each level in the game.
 */
public interface LevelInformation {

    /**
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * @return the speed of the paddle in the level.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle in the level.
     */
    int paddleWidth();

    /**
     * @return Number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();

    /**
     * The initial velocity of each ball.
     * @return the list of velocities for the balls in the level.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return list of blocks for the level.
     */
    List<Block> blocks();
}
