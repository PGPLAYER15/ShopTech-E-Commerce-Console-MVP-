package org.example.patterns.strategy;

/**
 * Estrategia de pago con PayPal.
 * 
 * <p>
 * Implementa el pago mediante cuenta de PayPal,
 * validando el formato del email.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Strategy (Behavioral Pattern)</li>
 * <li><b>Rol:</b> Concrete Strategy</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see PaymentStrategy
 */
public class PayPalStrategy implements PaymentStrategy {

    /** Email de la cuenta PayPal */
    String email;

    /**
     * Crea una estrategia de pago con PayPal.
     * 
     * @param email Email de la cuenta PayPal
     * @throws IllegalArgumentException si el email es invalido
     */
    public PayPalStrategy(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    /**
     * Procesa el pago con PayPal.
     * 
     * @param amount Monto a pagar
     * @return true siempre (simulacion exitosa)
     */
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid:  " + amount + " using PayPal.");
        return true;
    }
}
