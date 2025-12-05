package org.example.model.order;

import org.example.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum Status {
        PENDING, SHIPPED, DELIVERED, CANCELED
    }

    // valores obligatorios

    String orderId;
    User user;
    List<Product> items;
    double totalAmount;
    Status orderStatus;

    // valores opcionales

    String paymentMethod;
    String giftNote;

    public String getOrderId(){ return orderId;}
    public User getUser(){ return user; }
    public List<Product> getItems(){ return items; }
    public double getTotalAmount(){ return totalAmount;}
    public Status getOrderStatus(){ return orderStatus; }
    public String getPaymentMethod(){ return paymentMethod; }
    public String getGiftNote(){ return giftNote; }

    private Order(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.user = builder.user;
        this.items = new ArrayList<>(builder.items);
        this.totalAmount = builder.totalAmount;
        this.paymentMethod = builder.paymentMethod;
        this.orderStatus = builder.orderStatus;
        this.giftNote = builder.giftNote;
    }

    public static class OrderBuilder {
        String orderId;
        User user;
        List<Product> items;
        double totalAmount;
        Order.Status orderStatus = Status.PENDING;
        String paymentMethod;
        String giftNote;

        public OrderBuilder setOrderId(String orderId) {

            if(orderId == null || orderId.isEmpty()){
                throw new IllegalArgumentException("Order ID cannot be null or empty");
            }

            this.orderId = orderId;
            return this;
        }

        public OrderBuilder setUser(User user) {

            if(user == null){
                throw new IllegalArgumentException("User cannot be null");
            }

            this.user = user;
            return this;
        }

        public OrderBuilder setItems(List<Product> items) {

            if(items == null || items.isEmpty()){
                throw new IllegalArgumentException("Items cannot be null or empty");
            }

            this.items = new ArrayList<>(items);
            return this;
        }

        public OrderBuilder setOrderStatus(Order.Status orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderBuilder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public OrderBuilder setGiftNote(String giftNote) {
            this.giftNote = giftNote;
            return this;
        }

        public Order build() {

            if(orderId == null || user == null || items == null || items.isEmpty()){
                throw new IllegalStateException("Cannot create Order, missing required fields");
            }

            this.totalAmount = calculateTotal();

            return new Order(this);
        }

        private double calculateTotal() {
            double totalAmount = 0.0;

            for(Product item : items){
                totalAmount += item.getPrice();
            }

            if(totalAmount <= 0){
                throw new IllegalArgumentException("Total amount must be greater than zero");
            }

            return totalAmount;
        }

    }

}
