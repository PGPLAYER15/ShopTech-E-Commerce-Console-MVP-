package org.example.model.order;

/**
 * Observer interface for the Observer pattern.
 * Allows objects to be notified of changes in Order status.
 */
public interface OrderObserver {
    /**
     * Called when an order event occurs.
     * 
     * @param order The order that triggered the event
     * @param event Description of the event that occurred
     */
    void update(Order order, String event);
}
