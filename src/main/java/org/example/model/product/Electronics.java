package org.example.model.product;

/**
 * Representa un producto de la categoria Electronics.
 * 
 * <p>
 * Extiende la clase {@link Product} para productos electronicos
 * como laptops, smartphones, tablets, etc.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Factory Method:</b> Creado por {@code ElectronicsFactory}</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see Product
 * @see org.example.patterns.factory.ElectronicsFactory
 */
public class Electronics extends Product {

    /**
     * Crea un nuevo producto electronico.
     * 
     * @param id       Identificador unico del producto
     * @param name     Nombre del producto electronico
     * @param price    Precio en dolares
     * @param stock    Cantidad en inventario
     * @param category Subcategoria (ej: Computers, Smartphones)
     */
    public Electronics(String id, String name, double price, int stock, String category) {
        super(id, name, price, stock, category);
    }

    /**
     * Obtiene los detalles formateados del producto electronico.
     * 
     * @return String con formato "Electronics [ID: x, Name: x, Price: $x]"
     */
    @Override
    public String getDetails() {
        return "Electronics [ID: " + getId() + ", Name: " + getName() + ", Price: $" + getPrice() + "]";
    }
}
