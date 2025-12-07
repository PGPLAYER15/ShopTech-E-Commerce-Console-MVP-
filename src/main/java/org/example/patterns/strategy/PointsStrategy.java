package org.example.patterns.strategy;

/**
 * Estrategia de pago con puntos de recompensa.
 * 
 * <p>
 * Implementa el pago mediante puntos acumulados,
 * donde 100 puntos equivalen a $1.00.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Strategy (Behavioral Pattern)</li>
 * <li><b>Rol:</b> Concrete Strategy</li>
 * </ul>
 * 
 * <h2>Conversion:</h2>
 * <p>
 * 100 puntos = $1.00 USD
 * </p>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see PaymentStrategy
 */
public class PointsStrategy implements PaymentStrategy {

    /** Puntos disponibles del usuario */
    private int availablePoints;

    /**
     * Crea una estrategia de pago con puntos.
     * 
     * @param availablePoints Puntos disponibles del usuario
     * @throws IllegalArgumentException si los puntos son negativos
     */
    public PointsStrategy(int availablePoints) {
        if (availablePoints < 0) {
            throw new IllegalArgumentException("Available points cannot be negative");
        }
        this.availablePoints = availablePoints;
    }

    /**
     * Procesa el pago con puntos de recompensa.
     * 
     * <p>
     * Conversion: 100 puntos = $1.00
     * </p>
     * 
     * @param amount Monto a pagar
     * @return true si hay puntos suficientes, false si no
     * @throws IllegalArgumentException si el monto es <= 0
     */
    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        int pointsNeeded = (int) (amount * 100);

        if (availablePoints >= pointsNeeded) {
            System.out.println(
                    "Paid: " + amount + " using Points. Remaining points: " + (availablePoints - (int) (amount * 100)));
            availablePoints -= pointsNeeded;
            return true;
        }
        int missingPoints = pointsNeeded - availablePoints;

        System.out.println("Insufficient points. Available points: " + availablePoints + " Required amount: "
                + pointsNeeded + "Missing points: " + missingPoints);
        return false;
    }
}
