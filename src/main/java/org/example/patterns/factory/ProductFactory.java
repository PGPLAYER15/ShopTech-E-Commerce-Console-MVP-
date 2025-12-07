package org.example.patterns.factory;

import org.example.model.product.Clothing;
import org.example.model.product.Electronics;
import org.example.model.product.Product;

/**
 * Clase abstracta base para el patron Factory Method.
 * 
 * <p>
 * Define la interfaz para crear productos sin especificar
 * la clase concreta. Las subclases implementan el metodo
 * {@link #createProduct} para crear tipos especificos.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Factory Method (Creational Pattern)</li>
 * <li><b>Rol:</b> Creator (abstracto)</li>
 * <li><b>Producto:</b> {@link Product}</li>
 * </ul>
 * 
 * <h2>Implementaciones:</h2>
 * <ul>
 * <li>{@link ElectronicsFactory} - Crea productos electronicos</li>
 * <li>{@link ClothingFactory} - Crea productos de ropa</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see ElectronicsFactory
 * @see ClothingFactory
 * @see FactoryRegistry
 */
public abstract class ProductFactory {

    /**
     * Factory Method para crear un producto.
     * 
     * <p>
     * Las subclases implementan este metodo para crear
     * el tipo especifico de producto.
     * </p>
     * 
     * @param id       Identificador unico del producto
     * @param name     Nombre del producto
     * @param price    Precio en dolares
     * @param stock    Cantidad en inventario
     * @param category Categoria del producto
     * @return Nueva instancia del producto
     */
    public abstract Product createProduct(String id, String name, double price, int stock, String category);
}
