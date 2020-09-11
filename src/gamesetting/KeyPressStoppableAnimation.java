package gamesetting;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * KeyPressStoppableAnimation class.
 * The class role is decorator pattern for the end screen and pause screen.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Construct a KeyPressStoppableAnimation object.
     * @param sensor that represents the keyboard.
     * @param key that represents the string that we want to initialize.
     * @param animation that represents the kind of animation - end or pause screen.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // show the the animation
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        }
        if (!this.keyboardSensor.isPressed(this.key)) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
