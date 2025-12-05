package org.example.config;

import org.example.model.product.Product;
import org.example.patterns.factory.FactoryRegistry;

import java.util.ArrayList;

public enum StoreDatabase {
    INSTANCE;

    private final ArrayList<Product> products = new ArrayList<>();
    private final FactoryRegistry factoryRegistry = new FactoryRegistry();

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }

 
    public FactoryRegistry getFactoryRegistry() {
        return factoryRegistry;
    }

    
    public void initMockData() {
        System.out.println("LOG: Inicializando datos de prueba (Seeding)...");


        addProduct(factoryRegistry.createProduct("ELECTRONICS", "E001", "Laptop Dell XPS", 1500.00, 10, "Computers"));
        addProduct(factoryRegistry.createProduct("ELECTRONICS", "E002", "iPhone 15 Pro", 1200.00, 25, "Smartphones"));
        addProduct(factoryRegistry.createProduct("ELECTRONICS", "E003", "Monitor LG 27'", 300.50, 15, "Monitors"));
        addProduct(factoryRegistry.createProduct("CLOTHING", "C001", "Nike T-Shirt", 25.00, 50, "Apparel"));
        addProduct(factoryRegistry.createProduct("CLOTHING", "C002", "Levi's Jeans", 79.99, 30, "Apparel"));

        System.out.println("LOG: " + products.size() + " productos cargados en memoria.");
    }
}
