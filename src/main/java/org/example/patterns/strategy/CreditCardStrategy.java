package org.example.patterns.strategy;

/**
 * Estrategia de pago con tarjeta de credito.
 * 
 * <p>
 * Implementa el pago mediante tarjeta de credito,
 * validando el formato del numero de tarjeta.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Strategy (Behavioral Pattern)</li>
 * <li><b>Rol:</b> Concrete Strategy</li>
 * </ul>
 * 
 * <h2>Validaciones:</h2>
 * <ul>
 * <li>Numero de tarjeta: 16 digitos</li>
 * <li>Todos los campos requeridos</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see PaymentStrategy
 */
public class CreditCardStrategy implements PaymentStrategy {

    /** Numero de tarjeta (enmascarado) */
    private String cardNumber;

    /** Nombre del titular */
    private String OwnerCard;

    /** Fecha de expiracion */
    private String dateExpired;

    /**
     * Crea una estrategia de pago con tarjeta.
     * 
     * @param cardNumber  Numero de tarjeta (16 digitos)
     * @param ownerCard   Nombre del titular
     * @param dateExpired Fecha de expiracion (MM/YY)
     * @throws IllegalArgumentException si algun parametro es invalido
     */
    public CreditCardStrategy(String cardNumber, String ownerCard, String dateExpired) {
        if (cardNumber == null || ownerCard == null || dateExpired == null) {
            throw new IllegalArgumentException("All parameters must be provided");
        }

        if (!cardNumber.matches("\\d{16}")) {
            throw new IllegalArgumentException("Card number must contain only digits");
        }

        // Enmascarar numero de tarjeta por seguridad
        StringBuilder cardNumberMasked = new StringBuilder();
        for (int i = 0; i < cardNumber.length() - 4; i++) {
            cardNumberMasked.append("*");
        }
        cardNumberMasked.append(cardNumber.substring(cardNumber.length() - 4));

        this.cardNumber = cardNumberMasked.toString();
        this.OwnerCard = ownerCard;
        this.dateExpired = dateExpired;
    }

    /**
     * Procesa el pago con tarjeta de credito.
     * 
     * @param amount Monto a pagar
     * @return true siempre (simulacion exitosa)
     */
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid: " + amount + " using Credit Card.");
        return true;
    }
}
