package org.example.patterns.strategy;

public class PayPalStrategy implements PaymentStrategy {

    String email;

    public PayPalStrategy(String email) {
        if(email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    @Override
    public boolean pay(double amount) {

        System.out.println("Paid:  " + amount + " using PayPal.");
        return true;
    }
}
