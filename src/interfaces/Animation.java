package interfaces;
// ID: 316554641

import biuoop.DrawSurface;

/**
 * Animation interface.
 * The interface role is to represent the options and methods that each kind of animation needs to have.
 */
public interface Animation {

    /**
     * The method role is to represent just one frame - what and when to do.
     * @param d that represents the surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * The method role is to notice when the frame needed to stop.
     * @return boolean that will tell us what to do with the frame - close or not.
     */
    boolean shouldStop();
}
