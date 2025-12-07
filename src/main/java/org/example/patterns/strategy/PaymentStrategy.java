package org.example.patterns.strategy;

/**
 * Interfaz Strategy para metodos de pago.
 * 
 * <p>
 * Define la interfaz comun para todos los algoritmos de pago,
 * permitiendo cambiar el metodo de pago en tiempo de ejecucion.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Strategy (Behavioral Pattern)</li>
 * <li><b>Rol:</b> Strategy (interfaz)</li>
 * </ul>
 * 
 * <h2>Implementaciones:</h2>
 * <ul>
 * <li>{@link CreditCardStrategy} - Pago con tarjeta de credito</li>
 * <li>{@link PayPalStrategy} - Pago con PayPal</li>
 * <li>{@link PointsStrategy} - Pago con puntos de recompensa</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see CreditCardStrategy
 * @see PayPalStrategy
 * @see PointsStrategy
 */
public interface PaymentStrategy {

    /**
     * Procesa un pago por el monto especificado.
     * 
     * @param amount Monto a pagar en dolares
     * @return true si el pago fue exitoso, false si fallo
     */
    boolean pay(double amount);
}
