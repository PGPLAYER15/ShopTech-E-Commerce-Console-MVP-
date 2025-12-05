package org.example.patterns.factory;

import org.example.model.product.Clothing;
import org.example.model.product.Electronics;
import org.example.model.product.Product;

public abstract class ProductFactory {
    // Factory Method
    public abstract Product createProduct(String id, String name, double price, int stock, String category);


}

