package org.example.model.order;

import org.example.model.product.Product;
import org.example.patterns.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una orden de compra en el sistema.
 * 
 * <p>
 * Implementa multiples patrones de diseno:
 * </p>
 * <ul>
 * <li><b>Builder:</b> Construccion flexible via {@link OrderBuilder}</li>
 * <li><b>Observer:</b> Notifica a usuarios sobre cambios de estado</li>
 * <li><b>Strategy:</b> Metodos de pago intercambiables</li>
 * </ul>
 * 
 * <h2>Ejemplo de Uso:</h2>
 * 
 * <pre>{@code
 * Order order = new Order.OrderBuilder()
 *     .setOrderId("ORD-001")
 *     .setUser(user)
 *     .setItems(cartItems)
 *     .setPaymentMethod(new CreditCardStrategy(...))
 *     .build();
 * 
 * order.processPayment();
 * }</pre>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see OrderBuilder
 * @see OrderObserver
 * @see PaymentStrategy
 */
public class Order {

    /**
     * Estados posibles de una orden.
     */
    public enum Status {
        /** Orden creada, pendiente de pago */
        PENDING,
        /** Pago procesado exitosamente */
        PAID,
        /** Orden enviada al cliente */
        SHIPPED,
        /** Orden entregada al cliente */
        DELIVERED,
        /** Orden cancelada */
        CANCELED
    }

    // ==================== CAMPOS OBLIGATORIOS ====================

    /** Lista de observadores suscritos */
    List<OrderObserver> observers = new ArrayList<>();

    /** Identificador unico de la orden */
    String orderId;

    /** Usuario propietario de la orden */
    User user;

    /** Productos incluidos en la orden */
    List<Product> items;

    /** Monto total de la orden */
    double totalAmount;

    /** Estado actual de la orden */
    Status orderStatus;

    // ==================== CAMPOS OPCIONALES ====================

    /** Estrategia de pago seleccionada */
    PaymentStrategy paymentMethod;

    /** Nota de regalo (opcional) */
    String giftNote;

    // ==================== GETTERS ====================

    /** @return ID de la orden */
    public String getOrderId() {
        return orderId;
    }

    /** @return Usuario propietario */
    public User getUser() {
        return user;
    }

    /** @return Lista de productos */
    public List<Product> getItems() {
        return items;
    }

    /** @return Monto total */
    public double getTotalAmount() {
        return totalAmount;
    }

    /** @return Estado actual */
    public Status getOrderStatus() {
        return orderStatus;
    }

    /** @return Metodo de pago configurado */
    public PaymentStrategy getPaymentMethod() {
        return paymentMethod;
    }

    /** @return Nota de regalo si existe */
    public String getGiftNote() {
        return giftNote;
    }

    /**
     * Constructor privado - usar OrderBuilder.
     * 
     * @param builder Builder con los datos de la orden
     */
    private Order(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.user = builder.user;
        this.items = new ArrayList<>(builder.items);
        this.totalAmount = builder.totalAmount;
        this.paymentMethod = builder.paymentMethod;
        this.orderStatus = builder.orderStatus;
        this.giftNote = builder.giftNote;

        subscribe(builder.user);
    }

    // ==================== METODOS DE PAGO ====================

    /**
     * Procesa el pago usando la estrategia configurada.
     * 
     * <p>
     * Implementa el patron Strategy para pagos.
     * </p>
     * 
     * @throws IllegalStateException    si no hay metodo de pago o estado invalido
     * @throws IllegalArgumentException si el monto es invalido
     * @throws RuntimeException         si el pago falla
     */
    public void processPayment() {
        if (paymentMethod == null)
            throw new IllegalStateException("Payment method not set");

        if (orderStatus != Status.PENDING) {
            throw new IllegalStateException("Order status is not PENDING");
        }

        if (totalAmount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        if (paymentMethod.pay(this.totalAmount)) {
            setStatus(Status.PAID);
        } else {
            orderStatus = Status.PENDING;
            throw new RuntimeException("Payment failed for order: " + orderId);
        }
    }

    // ==================== METODOS OBSERVER ====================

    /**
     * Cambia el estado de la orden y notifica a los observadores.
     * 
     * @param newStatus Nuevo estado de la orden
     * @throws IllegalArgumentException si el estado es null
     */
    public void setStatus(Status newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        Status oldStatus = orderStatus;
        this.orderStatus = newStatus;
        notifyObservers("Order status changed from " + oldStatus + " to " + newStatus);
    }

    /**
     * Suscribe un observador a la orden.
     * 
     * @param user Observador a suscribir
     * @throws IllegalArgumentException si el usuario es null
     */
    public void subscribe(OrderObserver user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        observers.add(user);
    }

    /**
     * Desuscribe un observador de la orden.
     * 
     * @param user Observador a desuscribir
     * @throws IllegalArgumentException si el usuario es null
     * @throws IllegalStateException    si el usuario no esta suscrito
     */
    public void unsubscribe(OrderObserver user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (!observers.contains(user)) {
            throw new IllegalStateException("User is not subscribed");
        }

        observers.remove(user);
    }

    /**
     * Notifica a todos los observadores sobre un evento.
     * 
     * @param event Descripcion del evento
     * @throws IllegalArgumentException si el evento es null o vacio
     */
    public void notifyObservers(String event) {
        if (event == null || event.isEmpty()) {
            throw new IllegalArgumentException("Event cannot be null or empty");
        }

        for (OrderObserver observer : observers) {
            observer.update(this, event);
        }
    }

    // ==================== BUILDER ====================

    /**
     * Builder para construir ordenes de forma flexible.
     * 
     * <p>
     * Implementa el patron Builder para permitir la construccion
     * paso a paso de ordenes con parametros opcionales.
     * </p>
     * 
     * <h2>Ejemplo:</h2>
     * 
     * <pre>{@code
     * Order order = new Order.OrderBuilder()
     *         .setOrderId("ORD-001")
     *         .setUser(user)
     *         .setItems(items)
     *         .setGiftNote("Feliz cumpleanos!")
     *         .build();
     * }</pre>
     */
    public static class OrderBuilder {
        String orderId;
        User user;
        List<Product> items;
        double totalAmount;
        Order.Status orderStatus = Status.PENDING;
        PaymentStrategy paymentMethod;
        String giftNote;

        /**
         * Establece el ID de la orden.
         * 
         * @param orderId ID unico de la orden
         * @return this para encadenamiento
         * @throws IllegalArgumentException si el ID es null o vacio
         */
        public OrderBuilder setOrderId(String orderId) {
            if (orderId == null || orderId.isEmpty()) {
                throw new IllegalArgumentException("Order ID cannot be null or empty");
            }
            this.orderId = orderId;
            return this;
        }

        /**
         * Establece el usuario de la orden.
         * 
         * @param user Usuario propietario
         * @return this para encadenamiento
         * @throws IllegalArgumentException si el usuario es null
         */
        public OrderBuilder setUser(User user) {
            if (user == null) {
                throw new IllegalArgumentException("User cannot be null");
            }
            this.user = user;
            return this;
        }

        /**
         * Establece los items de la orden.
         * 
         * @param items Lista de productos
         * @return this para encadenamiento
         * @throws IllegalArgumentException si los items son null o vacios
         */
        public OrderBuilder setItems(List<Product> items) {
            if (items == null || items.isEmpty()) {
                throw new IllegalArgumentException("Items cannot be null or empty");
            }
            this.items = new ArrayList<>(items);
            return this;
        }

        /**
         * Establece el estado inicial de la orden.
         * 
         * @param orderStatus Estado inicial
         * @return this para encadenamiento
         */
        public OrderBuilder setOrderStatus(Order.Status orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        /**
         * Establece el metodo de pago.
         * 
         * @param paymentMethod Estrategia de pago
         * @return this para encadenamiento
         */
        public OrderBuilder setPaymentMethod(PaymentStrategy paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        /**
         * Establece una nota de regalo (opcional).
         * 
         * @param giftNote Mensaje de regalo
         * @return this para encadenamiento
         */
        public OrderBuilder setGiftNote(String giftNote) {
            this.giftNote = giftNote;
            return this;
        }

        /**
         * Construye la orden con los parametros configurados.
         * 
         * @return Nueva instancia de Order
         * @throws IllegalStateException si faltan campos obligatorios
         */
        public Order build() {
            if (orderId == null || user == null || items == null || items.isEmpty()) {
                throw new IllegalStateException("Cannot create Order, missing required fields");
            }

            this.totalAmount = calculateTotal();

            return new Order(this);
        }

        /**
         * Calcula el total de la orden.
         * 
         * @return Suma de precios de todos los items
         * @throws IllegalArgumentException si el total es menor o igual a 0
         */
        private double calculateTotal() {
            double totalAmount = 0.0;

            for (Product item : items) {
                totalAmount += item.getPrice();
            }

            if (totalAmount <= 0) {
                throw new IllegalArgumentException("Total amount must be greater than zero");
            }

            return totalAmount;
        }
    }
}
