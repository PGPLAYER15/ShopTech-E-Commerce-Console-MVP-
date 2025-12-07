package org.example.patterns.adapter;

import org.example.model.order.Order;

import java.util.ArrayList;
import java.util.List;

public class LegacyAccountingSystem {
    private List<VentaLegacy> recordedSales = new ArrayList<>();

    public void registrarVenta(String idOrden, String nombreCliente, double monto) {

        if (idOrden == null || idOrden.isEmpty()) {
            throw new IllegalArgumentException("ID de orden no puede ser nulo o vacío");
        }
        if (nombreCliente == null || nombreCliente.isEmpty()) {
            throw new IllegalArgumentException("Nombre del cliente no puede ser nulo o vacío");
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

    private static class VentaLegacy {
        private String idOrden;
        private String cliente;
        private double monto;

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

        public String getIdOrden() {
            return idOrden;
        }

        public String getNombreCliente() {
            return cliente;
        }

        public double getMonto() {
            return monto;
        }
    }
}
