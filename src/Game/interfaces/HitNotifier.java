// 211970389 Yonatan Zohar
package Game.interfaces;

/**
 * The HitNotifier interface represents an object that can notify hit
 * events to registered listeners.
 */
public interface HitNotifier {
    /**
     * Adds the specified HitListener as a listener to hit events.
     *
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes the specified HitListener from the list of listeners
     * to hit events.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
