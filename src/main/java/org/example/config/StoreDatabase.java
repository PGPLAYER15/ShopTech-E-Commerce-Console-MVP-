package org.example.config;

import org.example.model.product.Product;
import org.example.patterns.factory.FactoryRegistry;

import java.util.ArrayList;

/**
 * Patron Singleton que gestiona el inventario unico de productos.
 * 
 * <p>
 * Esta clase garantiza que solo exista una instancia del inventario
 * durante toda la ejecucion de la aplicacion, permitiendo un acceso
 * centralizado y consistente a los productos disponibles.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Singleton (Creational Pattern)</li>
 * <li><b>Proposito:</b> Garantizar una unica instancia</li>
 * <li><b>Implementacion:</b> Enum-based (thread-safe)</li>
 * </ul>
 * 
 * <h2>Ejemplo de Uso:</h2>
 * 
 * <pre>{@code
 * StoreDatabase store = StoreDatabase.INSTANCE;
 * store.addProduct(laptop);
 * Product found = store.getProduct("LAP-001");
 * }</pre>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 */
public enum StoreDatabase {
    /** Instancia unica del inventario (Singleton) */
    INSTANCE;

    private final ArrayList<Product> products = new ArrayList<>();
    private final FactoryRegistry factoryRegistry = new FactoryRegistry();

    /**
     * Agrega un producto al inventario.
     * 
     * @param product El producto a agregar
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Busca un producto por su ID.
     * 
     * @param id Identificador unico del producto
     * @return El producto encontrado o null si no existe
     */
    public Product getProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Obtiene todos los productos del inventario.
     * 
     * @return Lista de todos los productos disponibles
     */
    public ArrayList<Product> getAllProducts() {
        return products;
    }

    /**
     * Obtiene el registro de factories para crear productos.
     * 
     * @return El FactoryRegistry con las factories registradas
     */
    public FactoryRegistry getFactoryRegistry() {
        return factoryRegistry;
    }

    /**
     * Inicializa datos de prueba en el inventario.
     * Carga productos de ejemplo para demostracion.
     */
    public void initMockData() {
        System.out.println("LOG: Inicializando datos de prueba (Seeding)...");

        addProduct(factoryRegistry.createProduct("ELECTRONICS", "E001", "Laptop Dell XPS", 1500.00, 10, "Computers"));
        addProduct(factoryRegistry.createProduct("ELECTRONICS", "E002", "iPhone 15 Pro", 1200.00, 25, "Smartphones"));
        addProduct(factoryRegistry.createProduct("ELECTRONICS", "E003", "Monitor LG 27'", 300.50, 15, "Monitors"));
        addProduct(factoryRegistry.createProduct("CLOTHING", "C001", "Nike T-Shirt", 25.00, 50, "Apparel"));
        addProduct(factoryRegistry.createProduct("CLOTHING", "C002", "Levi's Jeans", 79.99, 30, "Apparel"));

        System.out.println("LOG: " + products.size() + " productos cargados en memoria.");
    }
}
