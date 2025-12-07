package org.example.model.order;

/**
 * Representa un usuario del sistema e implementa el patron Observer.
 * 
 * <p>
 * El usuario puede suscribirse a ordenes y recibir notificaciones
 * automaticas cuando el estado de la orden cambia.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Observer (Behavioral Pattern)</li>
 * <li><b>Rol:</b> Concrete Observer</li>
 * <li><b>Subject:</b> {@link Order}</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see OrderObserver
 * @see Order
 */
public class User implements OrderObserver {

    /** Identificador unico del usuario */
    private final int id;

    /** Nombre completo del usuario */
    private String name;

    /** Correo electronico del usuario */
    private String email;

    /** Direccion de envio */
    private String shippingAddress;

    /** Puntos de recompensa acumulados */
    private int rewardPoints;

    /**
     * Crea un nuevo usuario.
     * 
     * @param id              Identificador unico
     * @param name            Nombre completo
     * @param email           Correo electronico
     * @param shippingAddress Direccion de envio
     */
    public User(int id, String name, String email, String shippingAddress) {
        this.id = id;
        this.setName(name);
        this.setEmail(email);
        this.setShippingAddress(shippingAddress);
        setRewardPoints(0);
    }

    /**
     * Recibe notificacion de cambios en la orden (Patron Observer).
     * Simula el envio de un email al usuario.
     * 
     * @param order La orden que genero el evento
     * @param event Mensaje describiendo el cambio
     */
    @Override
    public void update(Order order, String event) {
        System.out.println("EMAIL sent to " + email);
        System.out.println("   To: " + name);
        System.out.println("   Subject: Order Update - " + order.getOrderId());
        System.out.println("   Message: " + event);
        System.out.println("   Total: $" + order.getTotalAmount());
        System.out.println();
    }

    // ==================== GETTERS ====================

    /**
     * @return Identificador unico del usuario
     */
    public int getId() {
        return id;
    }

    /**
     * @return Nombre completo del usuario
     */
    public String getName() {
        return name;
    }

    /**
     * @return Puntos de recompensa acumulados
     */
    public int getRewardPoints() {
        return rewardPoints;
    }

    /**
     * @return Correo electronico del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Direccion de envio del usuario
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    // ==================== SETTERS ====================

    /**
     * Establece el nombre del usuario.
     * 
     * @param name Nuevo nombre
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Establece el email del usuario.
     * 
     * @param email Nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Establece la direccion de envio.
     * 
     * @param shippingAddress Nueva direccion
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Establece los puntos de recompensa.
     * 
     * @param rewardPoints Nueva cantidad de puntos
     */
    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
