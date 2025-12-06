package org.example.model.order;

import org.example.model.product.Product;
import org.example.patterns.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum Status {
        PENDING,PAID, SHIPPED, DELIVERED, CANCELED
    }

    // valores obligatorios
    List<OrderObserver> observers = new ArrayList<>();
    String orderId;
    User user;
    List<Product> items;
    double totalAmount;
    Status orderStatus;

    // valores opcionales

    PaymentStrategy paymentMethod;
    String giftNote;

    public String getOrderId(){ return orderId;}
    public User getUser(){ return user; }
    public List<Product> getItems(){ return items; }
    public double getTotalAmount(){ return totalAmount;}
    public Status getOrderStatus(){ return orderStatus; }
    public PaymentStrategy getPaymentMethod(){ return paymentMethod; }
    public String getGiftNote(){ return giftNote; }

    private Order(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.user = builder.user;
        this.items = new ArrayList<>(builder.items);
        this.totalAmount = builder.totalAmount;
        this.paymentMethod = builder.paymentMethod;
        this.orderStatus = builder.orderStatus;
        this.giftNote = builder.giftNote;

        subscribe(builder.user);
    }

    // methods

    public void processPayment() {

        if(paymentMethod == null) throw new IllegalStateException("Payment method not set");

        if(orderStatus != Status.PENDING){
            throw new IllegalStateException("Order status is not PENDING");
        }

        if(totalAmount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        if (paymentMethod.pay(this.totalAmount)) {
            setStatus(Status.PAID);
        } else {
            orderStatus = Status.PENDING;
            throw new RuntimeException("Payment failed for order: " + orderId);
        }
    }

    public void setStatus(Status newStatus) {
        if(newStatus == null){
            throw new IllegalArgumentException("Status cannot be null");
        }
        Status oldStatus = orderStatus;
        this.orderStatus = newStatus;
        notifyObservers("Order status changed from " + oldStatus + " to " + newStatus);
    }

    public void subscribe(OrderObserver user) {
        if(user == null){
            throw new IllegalArgumentException("User cannot be null");
        }
        observers.add(user);
    }

    public void unsubscribe(OrderObserver user) {
        if(user == null){
            throw new IllegalArgumentException("User cannot be null");
        }

        if(!observers.contains(user)){
            throw new IllegalStateException("User is not subscribed");
        }

        observers.remove(user);
    }

    public void notifyObservers(String event) {

        if(event == null || event.isEmpty()){
            throw new IllegalArgumentException("Event cannot be null or empty");
        }

        for (OrderObserver observer : observers) {
            observer.update(this, event);
        }
    }

    public static class OrderBuilder {
        String orderId;
        User user;
        List<Product> items;
        double totalAmount;
        Order.Status orderStatus = Status.PENDING;
        PaymentStrategy paymentMethod;
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

        public OrderBuilder setPaymentMethod(PaymentStrategy paymentMethod) {
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
