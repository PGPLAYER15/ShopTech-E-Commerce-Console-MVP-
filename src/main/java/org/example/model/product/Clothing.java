package org.example.model.product;

public class Clothing extends Product {
    public Clothing(String id, String name, double price, int stock, String category) {
        super(id, name, price, stock, category);
    }

    @Override
    public String getDetails() {
        return "Clothing [ID: " + getId() + ", Name: " + getName() + ", Price: $" + getPrice() + "]";
    }
    // estructura únicamente
}
