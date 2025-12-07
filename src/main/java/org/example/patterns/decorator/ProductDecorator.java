package org.example.patterns.decorator;

import org.example.model.product.Product;

public abstract class ProductDecorator extends Product {

    Product wrappedProduct;

    public ProductDecorator(Product product) {

        super(product.getId(), product.getName(), product.getPrice(),
                product.getStock(), product.getCategory());

        this.wrappedProduct = product;
    }


    public String getId() { return wrappedProduct.getId(); }
    public int getStock() { return wrappedProduct.getStock(); }
    public String getName() { return wrappedProduct.getName(); }
    public double getPrice() { return wrappedProduct.getPrice(); }
    public String getDetails(){return wrappedProduct.getDetails();}



}

