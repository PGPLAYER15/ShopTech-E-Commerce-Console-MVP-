package org.example.patterns.strategy;

public class CreditCardStrategy implements PaymentStrategy {
    private String cardNumber;
    private String OwnerCard;
    private String dateExpired;

    public CreditCardStrategy(String cardNumber, String ownerCard, String dateExpired) {
        if(cardNumber == null || ownerCard == null || dateExpired == null ) {
            throw new IllegalArgumentException("All parameters must be provided");
        }

        if(!cardNumber.matches("\\d{16}")) {
            throw new IllegalArgumentException("Card number must contain only digits");
        }


        StringBuilder cardNumberMasked = new StringBuilder();
        for (int i = 0; i < cardNumber.length() - 4; i++) {
            cardNumberMasked.append("*");
        }
        cardNumberMasked.append(cardNumber.substring(cardNumber.length() - 4));

        this.cardNumber = cardNumberMasked.toString();
        this.OwnerCard = ownerCard;
        this.dateExpired = dateExpired;
    }

    @Override
    public boolean pay(double amount) {

        System.out.println("Paid: " + amount + " using Credit Card.");
        return true;
    }
}
