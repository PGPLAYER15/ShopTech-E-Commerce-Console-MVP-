package org.example.patterns.factory;

import org.example.model.product.Clothing;
import org.example.model.product.Product;

/**
 * Factory concreta para crear productos Clothing.
 * 
 * <p>
 * Implementa el Factory Method para crear productos
 * de la categoria Clothing (camisetas, pantalones, etc.).
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Factory Method (Creational Pattern)</li>
 * <li><b>Rol:</b> Concrete Creator</li>
 * <li><b>Producto:</b> {@link Clothing}</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see ProductFactory
 * @see Clothing
 */
public class ClothingFactory extends ProductFactory {

    /**
     * Crea un nuevo producto Clothing.
     * 
     * @param id       Identificador unico
     * @param name     Nombre del producto
     * @param price    Precio en dolares
     * @param stock    Cantidad en inventario
     * @param category Subcategoria (Apparel, Outerwear, etc.)
     * @return Nueva instancia de Clothing
     */
    @Override
    public Product createProduct(String id, String name, double price, int stock, String category) {
        return new Clothing(id, name, price, stock, category);
    }
}
