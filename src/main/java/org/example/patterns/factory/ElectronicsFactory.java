package org.example.patterns.factory;

import org.example.model.product.Electronics;
import org.example.model.product.Product;

/**
 * Factory concreta para crear productos Electronics.
 * 
 * <p>
 * Implementa el Factory Method para crear productos
 * de la categoria Electronics (laptops, phones, tablets, etc.).
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Factory Method (Creational Pattern)</li>
 * <li><b>Rol:</b> Concrete Creator</li>
 * <li><b>Producto:</b> {@link Electronics}</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see ProductFactory
 * @see Electronics
 */
public class ElectronicsFactory extends ProductFactory {

    /**
     * Crea un nuevo producto Electronics.
     * 
     * @param id       Identificador unico
     * @param name     Nombre del producto
     * @param price    Precio en dolares
     * @param stock    Cantidad en inventario
     * @param category Subcategoria (Computers, Smartphones, etc.)
     * @return Nueva instancia de Electronics
     */
    @Override
    public Product createProduct(String id, String name, double price, int stock, String category) {
        return new Electronics(id, name, price, stock, category);
    }
}
