package org.example.patterns.factory;

import org.example.model.product.Clothing;
import org.example.model.product.Product;

public class ClothingFactory extends ProductFactory {
    @Override
    public Product createProduct(String id, String name, double price, int stock, String category) {
        return new Clothing(id,name,price,stock,category);
    }
}
