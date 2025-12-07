package org.example.model.order;
import org.example.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<Product> products = new ArrayList<>();
    double totalPrice;

    public void addProduct(Product product) {

        if(product.getStock() == 0){
            throw new IllegalArgumentException("Product is out of stock: " + product.getName());
        }
        System.out.println("Adding product to cart: " + product.getName());
        System.out.println("Current stock before adding to cart: " + product.getStock());
        product.decrementStock();
        totalPrice += product.getPrice();
        products.add(product);
        System.out.println("Product added to cart: " + product.getName());
    }

    public int getTotalItems() {
        return products.size();
    }

    public List<Product> getItems() {
        return new ArrayList<>(products);
    }

    public double getTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void clear() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void removeProduct(String id) {
        String temporalProductName = "";

        for( Product product : products) {
            if(product.getId().equals(id)) {
                product.incrementStock();
                products.remove(product);
            }
        }
        System.out.println("Product removed from cart: " + temporalProductName);
    }

    public void viewCart() {
        System.out.println("Total items: " + getTotalItems());
        System.out.println("Products in cart:");
        for (Product product : products) {
            System.out.println("- " + product.getName() + " | Price: $" + product.getPrice());
        }
        System.out.println("Total price: $" + totalPrice);
    }
}
