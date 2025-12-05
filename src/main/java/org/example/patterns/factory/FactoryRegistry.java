package org.example.patterns.factory;
import org.example.model.product.Product;

import java.util.HashMap;
import java.util.Map;

public class FactoryRegistry {
    private final Map<String, ProductFactory> factories;

    public FactoryRegistry() {
        this.factories = new HashMap<>();
        registerDefaultFactories();
    }


    private void registerDefaultFactories() {
        registerFactory("ELECTRONICS", new ElectronicsFactory());
        registerFactory("CLOTHING", new ClothingFactory());
    }

    public void registerFactory(String type, ProductFactory factory) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Product type cannot be null or empty");
        }
        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        factories.put(type.toUpperCase(), factory);
    }

    public Product createProduct(String type, String id, String name, double price, int stock, String category) {
        if (type == null) {
            throw new IllegalArgumentException("Product type cannot be null");
        }
        
        ProductFactory factory = factories.get(type.toUpperCase());
        
        if (factory == null) {
            throw new IllegalArgumentException("No factory registered for product type: " + type);
        }
        
        return factory.createProduct(id, name, price, stock, category);
    }

    
    public boolean hasFactory(String type) {
        return type != null && factories.containsKey(type.toUpperCase());
    }

    
    public java.util.Set<String> getRegisteredTypes() {
        return new java.util.HashSet<>(factories.keySet());
    }
}
