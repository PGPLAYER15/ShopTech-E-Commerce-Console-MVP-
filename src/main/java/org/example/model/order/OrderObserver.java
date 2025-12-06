package org.example.model.order;


public interface OrderObserver {
    void update(Order order, String event);
}
