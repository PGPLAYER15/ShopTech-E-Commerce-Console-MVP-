package org.example.patterns.decorator;

import org.example.model.product.Product;

public abstract class ProductDecorator {

    Product wrappedProduct;

    public ProductDecorator(Product product) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        this.wrappedProduct = product;
    }


    public String getId() { return wrappedProduct.getId(); }
    public int getStock() { return wrappedProduct.getStock(); }
    public String getName() { return wrappedProduct.getName(); }
    public double getPrice() { return wrappedProduct.getPrice(); }
    public String getDetails(){return wrappedProduct.getDetails();}



}

