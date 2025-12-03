package org.example.model.order;

public class User implements OrderObserver {
    // estructura únicamente
    @Override
    public void update(Order order, String event) {}
}
