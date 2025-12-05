package org.example.model.order;

public class User implements OrderObserver {
    int id;
    String name;
    String email;
    String shippingAddress;

    public User(int id, String name, String email, String shippingAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.shippingAddress = shippingAddress;
    }

    @Override
    public void update(Order order, String event) {}
}
