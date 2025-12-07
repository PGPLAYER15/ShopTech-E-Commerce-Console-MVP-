package org.example.model.product;

/**
 * Representa un producto de la categoria Clothing.
 * 
 * <p>
 * Extiende la clase {@link Product} para productos de ropa
 * como camisetas, pantalones, chaquetas, etc.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Factory Method:</b> Creado por {@code ClothingFactory}</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see Product
 * @see org.example.patterns.factory.ClothingFactory
 */
public class Clothing extends Product {

    /**
     * Crea un nuevo producto de ropa.
     * 
     * @param id       Identificador unico del producto
     * @param name     Nombre del producto de ropa
     * @param price    Precio en dolares
     * @param stock    Cantidad en inventario
     * @param category Subcategoria (ej: Apparel, Outerwear)
     */
    public Clothing(String id, String name, double price, int stock, String category) {
        super(id, name, price, stock, category);
    }

    /**
     * Obtiene los detalles formateados del producto de ropa.
     * 
     * @return String con formato "Clothing [ID: x, Name: x, Price: $x]"
     */
    @Override
    public String getDetails() {
        return "Clothing [ID: " + getId() + ", Name: " + getName() + ", Price: $" + getPrice() + "]";
    }
}
