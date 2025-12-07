package org.example.patterns.decorator;


import org.example.model.product.Product;

public class GiftWrapDecorator extends ProductDecorator {

    private final double GIFT_WRAP_COST = 10.00;

    public GiftWrapDecorator( Product product) {
        super(product);
    }

    public double getPrice() {
        return wrappedProduct.getPrice() + GIFT_WRAP_COST;
    }

    public String getDetails() {
        return wrappedProduct.getDetails() + "Includes gift wrapping.";
    }
}
