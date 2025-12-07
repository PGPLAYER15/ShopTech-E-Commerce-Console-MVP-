package org.example.patterns.adapter;

import org.example.model.order.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Sistema de contabilidad legacy con interfaz en espanol.
 * 
 * <p>
 * Representa un sistema antiguo que no es compatible
 * directamente con la interfaz moderna {@link AccountingService}.
 * Requiere un {@link AccountingAdapter} para integrarse.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Adapter (Structural Pattern)</li>
 * <li><b>Rol:</b> Adaptee (clase legacy)</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see AccountingAdapter
 */
public class LegacyAccountingSystem {

    /** Lista de ventas registradas */
    private List<VentaLegacy> recordedSales = new ArrayList<>();

    /**
     * Registra una venta en el sistema legacy.
     * 
     * <p>
     * Metodo con interfaz en espanol (sistema antiguo).
     * </p>
     * 
     * @param idOrden       ID de la orden
     * @param nombreCliente Nombre del cliente
     * @param monto         Monto total de la venta
     * @throws IllegalArgumentException si algun parametro es invalido
     */
    public void registrarVenta(String idOrden, String nombreCliente, double monto) {
        if (idOrden == null || idOrden.isEmpty()) {
            throw new IllegalArgumentException("ID de orden no puede ser nulo o vacio");
        }
        if (nombreCliente == null || nombreCliente.isEmpty()) {
            throw new IllegalArgumentException("Nombre del cliente no puede ser nulo o vacio");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        VentaLegacy venta = new VentaLegacy(idOrden, nombreCliente, monto);
        recordedSales.add(venta);

        System.out.println("═══════════════════════════════════════");
        System.out.println(" SISTEMA LEGACY - Contabilidad");
        System.out.println("Venta registrada exitosamente");
        System.out.println("ID Orden: " + idOrden);
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Monto: $" + monto);
        System.out.println("Total ventas: " + recordedSales.size());
        System.out.println("═══════════════════════════════════════");
    }

    /**
     * Clase interna que representa una venta en el sistema legacy.
     */
    private static class VentaLegacy {
        private String idOrden;
        private String cliente;
        private double monto;

        /**
         * Crea un registro de venta legacy.
         * 
         * @param idOrden ID de la orden
         * @param cliente Nombre del cliente
         * @param monto   Monto de la venta
         */
        VentaLegacy(String idOrden, String cliente, double monto) {
            if (idOrden == null || idOrden.isEmpty()) {
                throw new IllegalArgumentException("idOrden cannot be null or empty");
            }
            if (cliente == null || cliente.isEmpty()) {
                throw new IllegalArgumentException("cliente cannot be null or empty");
            }
            if (monto <= 0) {
                throw new IllegalArgumentException("monto must be greater than zero");
            }

            this.idOrden = idOrden;
            this.cliente = cliente;
            this.monto = monto;
        }

        /** @return ID de la orden */
        public String getIdOrden() {
            return idOrden;
        }

        /** @return Nombre del cliente */
        public String getNombreCliente() {
            return cliente;
        }

        /** @return Monto de la venta */
        public double getMonto() {
            return monto;
        }
    }
}
