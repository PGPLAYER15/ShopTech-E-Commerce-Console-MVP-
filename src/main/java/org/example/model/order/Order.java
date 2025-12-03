package org.example.model.order;

public class Order {
    // estructura únicamente
}

interface OrderObserver {
    // método de la interfaz (estructura únicamente)
    void update(Order order, String event);
}
