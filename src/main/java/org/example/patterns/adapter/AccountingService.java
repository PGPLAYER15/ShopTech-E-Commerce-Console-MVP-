package org.example.patterns.adapter;

import org.example.model.order.Order;

/**
 * Interfaz para servicios de contabilidad modernos.
 * 
 * <p>
 * Define el contrato que espera el sistema moderno
 * para registrar ventas.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Adapter (Structural Pattern)</li>
 * <li><b>Rol:</b> Target (interfaz esperada)</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see AccountingAdapter
 */
public interface AccountingService {

    /**
     * Registra una venta en el sistema de contabilidad.
     * 
     * @param order Orden completada a registrar
     */
    void logSale(Order order);
}
