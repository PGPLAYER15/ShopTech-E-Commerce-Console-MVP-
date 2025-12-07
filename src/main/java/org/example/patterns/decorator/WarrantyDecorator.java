package org.example.patterns.decorator;

import org.example.model.product.Product;

public class WarrantyDecorator extends ProductDecorator {

    private final double  WARRANTY_COST = 50.00;


    public WarrantyDecorator(Product product) {super(product); }

    public double getPrice() {
        return wrappedProduct.getPrice() + WARRANTY_COST;
    }

    public String getDetails() {
        return wrappedProduct.getDetails() + "Includes extended warranty for 2 years.";
    }
}

