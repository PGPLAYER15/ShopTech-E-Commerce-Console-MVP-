package org.example.patterns.decorator;

import org.example.model.product.Product;

/**
 * Decorador que agrega garantia extendida al producto.
 * 
 * <p>
 * Incrementa el precio del producto en $50.00 y agrega
 * informacion de garantia a los detalles.
 * </p>
 * 
 * <h2>Patron de Diseno:</h2>
 * <ul>
 * <li><b>Tipo:</b> Decorator (Structural Pattern)</li>
 * <li><b>Rol:</b> Concrete Decorator</li>
 * <li><b>Costo adicional:</b> $50.00</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 * @see ProductDecorator
 */
public class WarrantyDecorator extends ProductDecorator {

    /** Costo de la garantia extendida */
    private final double WARRANTY_COST = 50.00;

    /**
     * Crea un decorador de garantia para el producto.
     * 
     * @param product Producto a decorar
     */
    public WarrantyDecorator(Product product) {
        super(product);
    }

    /**
     * Retorna el precio del producto mas el costo de la garantia.
     * 
     * @return Precio original + $50.00
     */
    public double getPrice() {
        return wrappedProduct.getPrice() + WARRANTY_COST;
    }

    /**
     * Retorna los detalles del producto con informacion de garantia.
     * 
     * @return Detalles originales + texto de garantia
     */
    public String getDetails() {
        return wrappedProduct.getDetails() + "Includes extended warranty for 2 years.";
    }
}
