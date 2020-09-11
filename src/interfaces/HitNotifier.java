package interfaces;

/**
 * HitNotifier interface:
 * Allow us to add and remove listeners from the list.
 */

public interface HitNotifier {

    /**
     * Add hit listener.
     * @param hl that represents the listener that we want to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     * @param hl that represents the listener that we want to add.
     */
    void removeHitListener(HitListener hl);
}
