package org.example.patterns.adapter;

import org.example.model.order.Order;

/**
 * Adaptador que conecta el sistema moderno con el sistema legacy.
 * 
 * <p>
 * Traduce las llamadas de {@link AccountingService#logSale(Order)}
 * al formato esperado por {@link LegacyAccountingSystem#registrarVenta}.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Adapter (Structural Pattern)</li>
 * <li><b>Rol:</b> Adapter (clase adaptadora)</li>
 * <li><b>Target:</b> {@link AccountingService}</li>
 * <li><b>Adaptee:</b> {@link LegacyAccountingSystem}</li>
 * </ul>
 * 
 * <h2>Ejemplo de Uso:</h2>
 * 
 * <pre>{@code
 * LegacyAccountingSystem legacy = new LegacyAccountingSystem();
 * AccountingService adapter = new AccountingAdapter(legacy);
 * adapter.logSale(order); // Traduce a registrarVenta()
 * }</pre>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see AccountingService
 * @see LegacyAccountingSystem
 */
public class AccountingAdapter implements AccountingService {

    /** Sistema legacy a adaptar */
    private LegacyAccountingSystem legacyAccountingSystem;

    /**
     * Crea un adaptador para el sistema legacy.
     * 
     * @param legacyAccountingSystem Sistema legacy a adaptar
     * @throws IllegalArgumentException si el sistema es null
     */
    public AccountingAdapter(LegacyAccountingSystem legacyAccountingSystem) {
        if (legacyAccountingSystem == null) {
            throw new IllegalArgumentException("LegacyAccountingSystem cannot be null");
        }
        this.legacyAccountingSystem = legacyAccountingSystem;
    }

    /**
     * Adapta la llamada logSale() al metodo registrarVenta() del sistema legacy.
     * 
     * <p>
     * Extrae los datos de la orden y los traduce al formato
     * esperado por el sistema legacy.
     * </p>
     * 
     * @param order Orden a registrar
     * @throws IllegalArgumentException si la orden es null
     */
    @Override
    public void logSale(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        // Extraer datos de la orden
        String orderId = order.getOrderId();
        String customerName = order.getUser().getName();
        double amount = order.getTotalAmount();

        // Mostrar traduccion
        System.out.println("ADAPTER: Translating logSale() -> registrarVenta()");

        // Llamar al sistema legacy
        legacyAccountingSystem.registrarVenta(orderId, customerName, amount);
    }
}
