package org.example.model.product;

public abstract class Product {
    private String id;
    private String name;
    private double price;
    private int stock;
    private String category;

    public Product(String id, String name, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // getters

    public String getId() { return id; }
    public int getStock() { return stock; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public abstract String getDetails();

    // methods

    public void incrementStock() {
        this.stock += 1;
    }

    public void decrementStock() {
        if(this.stock > 0) {
            this.stock -= 1;
        } else {
            throw new IllegalStateException("Stock cannot be negative");
        }
    }
}
