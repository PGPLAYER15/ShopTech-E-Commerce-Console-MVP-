package org.example.patterns.strategy;

public class PointsStrategy implements PaymentStrategy{

    private int availablePoints;

    public PointsStrategy(int availablePoints) {
        if(availablePoints < 0) {
            throw new IllegalArgumentException("Available points cannot be negative");
        }
        this.availablePoints = availablePoints;
    }

    @Override
    public boolean pay(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        int pointsNeeded = (int)(amount * 100);

        if(availablePoints >= pointsNeeded ) {
            System.out.println("Paid: " + amount + " using Points. Remaining points: " + (availablePoints - (int)(amount * 100)));
            availablePoints -= pointsNeeded;
            return true;
        }
        int missingPoints = pointsNeeded - availablePoints ;

        System.out.println("Insufficient points. Available points: " + availablePoints + " Required amount: " + pointsNeeded + "Missing points: " + missingPoints  );
        return false;
    }
}
