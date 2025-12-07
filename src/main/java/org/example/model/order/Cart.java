package org.example.model.order;

import org.example.model.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el carrito de compras del usuario.
 * 
 * <p>
 * Gestiona la coleccion de productos que el usuario desea comprar,
 * incluyendo operaciones de agregar, remover y calcular totales.
 * </p>
 * 
 * <h2>Funcionalidades:</h2>
 * <ul>
 * <li>Agregar productos con validacion de stock</li>
 * <li>Remover productos restaurando stock</li>
 * <li>Calcular total del carrito</li>
 * <li>Visualizar contenido</li>
 * </ul>
 * 
 * @author Marco Vinicio Palazuelos Leon
 * @version 1.0
 * @since 2025
 */
public class Cart {

    /** Lista de productos en el carrito */
    List<Product> products = new ArrayList<>();

    /** Precio total acumulado */
    double totalPrice;

    /**
     * Agrega un producto al carrito.
     * Valida stock disponible y decrementa el inventario.
     * 
     * @param product Producto a agregar
     * @throws IllegalArgumentException si el producto no tiene stock
     */
    public void addProduct(Product product) {
        if (product.getStock() == 0) {
            throw new IllegalArgumentException("Product is out of stock: " + product.getName());
        }
        System.out.println("Adding product to cart: " + product.getName());
        System.out.println("Current stock before adding to cart: " + product.getStock());
        product.decrementStock();
        totalPrice += product.getPrice();
        products.add(product);
        System.out.println("Product added to cart: " + product.getName());
    }

    /**
     * Obtiene el numero total de items en el carrito.
     * 
     * @return Cantidad de productos en el carrito
     */
    public int getTotalItems() {
        return products.size();
    }

    /**
     * Obtiene una copia de los items del carrito.
     * 
     * @return Lista de productos (copia defensiva)
     */
    public List<Product> getItems() {
        return new ArrayList<>(products);
    }

    /**
     * Calcula el total del carrito.
     * 
     * @return Suma de precios de todos los productos
     */
    public double getTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    /**
     * Vacia el carrito completamente.
     */
    public void clear() {
        products.clear();
    }

    /**
     * Verifica si el carrito esta vacio.
     * 
     * @return true si no hay productos, false si hay al menos uno
     */
    public boolean isEmpty() {
        return products.isEmpty();
    }

    /**
     * Remueve un producto del carrito por su ID.
     * Restaura el stock del producto.
     * 
     * @param id Identificador del producto a remover
     */
    public void removeProduct(String id) {
        String temporalProductName = "";

        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.incrementStock();
                products.remove(product);
            }
        }
        System.out.println("Product removed from cart: " + temporalProductName);
    }

    /**
     * Muestra el contenido del carrito en consola.
     */
    public void viewCart() {
        System.out.println("Total items: " + getTotalItems());
        System.out.println("Products in cart:");
        for (Product product : products) {
            System.out.println("- " + product.getName() + " | Price: $" + product.getPrice());
        }
        System.out.println("Total price: $" + totalPrice);
    }
}
