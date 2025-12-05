package org.example.model.product;

public class Electronics extends Product {

    public Electronics(String id, String name, double price , int stock, String category) {
        super(id, name, price, stock, category);
    }

    @Override
    public String getDetails() {
        return "Electronics [ID: " + getId() + ", Name: " + getName() + ", Price: $" + getPrice() + "]";
    }

}
