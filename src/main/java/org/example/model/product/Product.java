package org.example.model.product;

/**
 * Clase abstracta base para todos los productos del sistema.
 * 
 * <p>
 * Define los atributos y comportamientos comunes que comparten
 * todos los tipos de productos (Electronics, Clothing, etc.).
 * </p>
 * 
 * <h2>Principios SOLID:</h2>
 * <ul>
 * <li><b>SRP:</b> Solo gestiona datos del producto</li>
 * <li><b>OCP:</b> Extensible via subclases sin modificacion</li>
 * <li><b>LSP:</b> Subclases pueden sustituir a Product</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see Electronics
 * @see Clothing
 */
public abstract class Product {

    /** Identificador unico del producto */
    private String id;

    /** Nombre del producto */
    private String name;

    /** Precio del producto en dolares */
    private double price;

    /** Cantidad disponible en inventario */
    private int stock;

    /** Categoria del producto */
    private String category;

    /**
     * Constructor para crear un nuevo producto.
     * 
     * @param id       Identificador unico del producto
     * @param name     Nombre descriptivo del producto
     * @param price    Precio en dolares (debe ser positivo)
     * @param stock    Cantidad inicial en inventario
     * @param category Categoria del producto
     */
    public Product(String id, String name, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // ==================== GETTERS ====================

    /**
     * Obtiene el ID del producto.
     * 
     * @return Identificador unico
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el stock actual.
     * 
     * @return Cantidad disponible en inventario
     */
    public int getStock() {
        return stock;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return Nombre descriptivo
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return Precio en dolares
     */
    public double getPrice() {
        return price;
    }

    /**
     * Obtiene los detalles del producto.
     * Metodo abstracto implementado por cada subclase.
     * 
     * @return String con los detalles formateados
     */
    public abstract String getDetails();

    /**
     * Obtiene la categoria del producto.
     * 
     * @return Categoria del producto
     */
    public String getCategory() {
        return category;
    }

    // ==================== METODOS DE STOCK ====================

    /**
     * Incrementa el stock en 1 unidad.
     * Usado cuando se remueve un producto del carrito.
     */
    public void incrementStock() {
        this.stock += 1;
    }

    /**
     * Decrementa el stock en 1 unidad.
     * Usado cuando se agrega un producto al carrito.
     * 
     * @throws IllegalStateException si el stock es 0
     */
    public void decrementStock() {
        if (this.stock > 0) {
            this.stock -= 1;
        } else {
            throw new IllegalStateException("Stock cannot be negative");
        }
    }

    /**
     * Establece un nuevo precio para el producto.
     * 
     * @param price Nuevo precio en dolares
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
