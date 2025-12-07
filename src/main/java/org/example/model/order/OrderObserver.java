package org.example.model.order;

/**
 * Interfaz Observer para el patron Observer.
 * 
 * <p>
 * Define el contrato para los observadores que desean ser notificados
 * cuando ocurren cambios en una orden.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Observer (Behavioral Pattern)</li>
 * <li><b>Rol:</b> Observer (interfaz)</li>
 * <li><b>Subject:</b> {@link Order}</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see Order
 * @see User
 */
public interface OrderObserver {

    /**
     * Metodo llamado cuando ocurre un evento en la orden.
     * 
     * @param order La orden que genero el evento
     * @param event Descripcion del evento ocurrido
     */
    void update(Order order, String event);
}
