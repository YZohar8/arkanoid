// 211970389 Yonatan Zohar
package Game.animtion;

import Game.interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class represents an animation
 * that can be stopped by a key press.
 * It implements the Animation interface.
 */
public class KeyPressStoppableAnimation implements Animation {
    // Define fields
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean flag;
    private boolean isAlreadyPressed;

    /**
     * Constructs a KeyPressStoppableAnimation object with the
     * specified keyboard sensor, key, and animation.
     *
     * @param sensor    The KeyboardSensor used to check key presses
     * @param key       The key that will stop the animation when pressed
     * @param animation The animation to be stopped
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.key = key;
        this.sensor = sensor;
        this.animation = animation;
        flag = false;
        isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if ((this.sensor.isPressed(key)) && !this.isAlreadyPressed) {
            flag = true;
        }
        if (!this.sensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return flag;
    }
}
