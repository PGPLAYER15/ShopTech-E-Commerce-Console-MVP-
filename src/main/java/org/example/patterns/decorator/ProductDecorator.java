package org.example.patterns.decorator;

import org.example.model.product.Product;

/**
 * Clase base abstracta para decoradores de productos.
 * 
 * <p>
 * Permite agregar funcionalidades adicionales a productos
 * sin modificar la clase original (principio OCP).
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Decorator (Structural Pattern)</li>
 * <li><b>Rol:</b> Decorator (abstracto)</li>
 * <li><b>Componente:</b> {@link Product}</li>
 * </ul>
 * 
 * <h2>Implementaciones:</h2>
 * <ul>
 * <li>{@link WarrantyDecorator} - Agrega garantia extendida</li>
 * <li>{@link GiftWrapDecorator} - Agrega envoltorio de regalo</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see WarrantyDecorator
 * @see GiftWrapDecorator
 */
public abstract class ProductDecorator extends Product {

    /** Producto envuelto por este decorador */
    Product wrappedProduct;

    /**
     * Crea un decorador que envuelve un producto.
     * 
     * @param product Producto a decorar
     */
    public ProductDecorator(Product product) {
        super(product.getId(), product.getName(), product.getPrice(),
                product.getStock(), product.getCategory());
        this.wrappedProduct = product;
    }

    /**
     * @return ID del producto envuelto
     */
    public String getId() {
        return wrappedProduct.getId();
    }

    /**
     * @return Stock del producto envuelto
     */
    public int getStock() {
        return wrappedProduct.getStock();
    }

    /**
     * @return Nombre del producto envuelto
     */
    public String getName() {
        return wrappedProduct.getName();
    }

    /**
     * @return Precio del producto (puede ser modificado por subclases)
     */
    public double getPrice() {
        return wrappedProduct.getPrice();
    }

    /**
     * @return Detalles del producto (puede ser modificado por subclases)
     */
    public String getDetails() {
        return wrappedProduct.getDetails();
    }
}
