package org.example.patterns.factory;

import org.example.model.product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Registro centralizado de factories de productos.
 * 
 * <p>
 * Gestiona el registro y acceso a las diferentes factories
 * de productos, permitiendo crear productos por tipo sin
 * conocer la factory concreta.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Registry + Factory Method</li>
 * <li><b>Proposito:</b> Centralizar la creacion de productos</li>
 * </ul>
 * 
 * <h2>Ejemplo de Uso:</h2>
 * 
 * <pre>{@code
 * FactoryRegistry registry = new FactoryRegistry();
 * Product laptop = registry.createProduct(
 *         "ELECTRONICS", "LAP-001", "Gaming Laptop", 1299.99, 10, "Computers");
 * }</pre>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see ProductFactory
 */
public class FactoryRegistry {

    /** Mapa de factories registradas por tipo */
    private final Map<String, ProductFactory> factories;

    /**
     * Crea un nuevo registro e inicializa factories por defecto.
     */
    public FactoryRegistry() {
        this.factories = new HashMap<>();
        registerDefaultFactories();
    }

    /**
     * Registra las factories por defecto (Electronics, Clothing).
     */
    private void registerDefaultFactories() {
        registerFactory("ELECTRONICS", new ElectronicsFactory());
        registerFactory("CLOTHING", new ClothingFactory());
    }

    /**
     * Registra una factory para un tipo de producto.
     * 
     * @param type    Tipo de producto (ej: "ELECTRONICS")
     * @param factory Factory que creara ese tipo
     * @throws IllegalArgumentException si type o factory son null
     */
    public void registerFactory(String type, ProductFactory factory) {
        if (type == null || factory == null) {
            throw new IllegalArgumentException("Category and factory cannot be null");
        }
        factories.put(type.toUpperCase(), factory);
    }

    /**
     * Crea un producto usando la factory correspondiente.
     * 
     * @param type     Tipo de producto
     * @param id       ID del producto
     * @param name     Nombre del producto
     * @param price    Precio
     * @param stock    Stock disponible
     * @param category Categoria
     * @return Nuevo producto creado
     * @throws IllegalArgumentException si el tipo es null o no hay factory
     */
    public Product createProduct(String type, String id, String name, double price, int stock, String category) {
        if (type == null) {
            throw new IllegalArgumentException("Product type cannot be null");
        }

        ProductFactory factory = factories.get(type.toUpperCase());

        if (factory == null) {
            throw new IllegalArgumentException("No factory registered for product type: " + type);
        }

        return factory.createProduct(id, name, price, stock, category);
    }

    /**
     * Verifica si existe una factory para un tipo.
     * 
     * @param type Tipo de producto a verificar
     * @return true si existe una factory registrada
     */
    public boolean hasFactory(String type) {
        return type != null && factories.containsKey(type.toUpperCase());
    }

    /**
     * Obtiene los tipos de productos registrados.
     * 
     * @return Set con los tipos disponibles
     */
    public java.util.Set<String> getRegisteredTypes() {
        return new java.util.HashSet<>(factories.keySet());
    }
}
