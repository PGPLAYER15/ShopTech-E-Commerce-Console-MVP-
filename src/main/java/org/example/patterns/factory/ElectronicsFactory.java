package org.example.patterns.factory;

import org.example.model.product.Electronics;
import org.example.model.product.Product;

public class ElectronicsFactory extends ProductFactory {
    @Override
    public Product createProduct(String id, String name, double price, int stock, String category) {
        return new Electronics( id,name,price,stock,category);
    }
}
