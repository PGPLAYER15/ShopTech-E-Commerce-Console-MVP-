package org.example;
import org.example.config.StoreDatabase;
import org.example.model.order.Cart;
import org.example.model.order.Order;
import org.example.model.order.User;
import org.example.model.product.Product;
import org.example.patterns.adapter.AccountingAdapter;
import org.example.patterns.adapter.AccountingService;
import org.example.patterns.adapter.LegacyAccountingSystem;
import org.example.patterns.decorator.GiftWrapDecorator;
import org.example.patterns.decorator.ProductDecorator;
import org.example.patterns.decorator.WarrantyDecorator;
import org.example.patterns.factory.ClothingFactory;
import org.example.patterns.factory.ElectronicsFactory;
import org.example.patterns.factory.FactoryRegistry;
import org.example.patterns.strategy.CreditCardStrategy;
import org.example.patterns.strategy.PayPalStrategy;
import org.example.patterns.strategy.PaymentStrategy;
import org.example.patterns.strategy.PointsStrategy;

import java.util.List;
import java.util.Scanner;

/**
 * ShopTech - E-Commerce Console Application
 *
 * Esta aplicación demuestra la implementación de 7 patrones de diseño:
 * 1. Singleton - StoreDatabase
 * 2. Factory Method - ProductFactory y FactoryRegistry
 * 3. Builder - Order.OrderBuilder
 * 4. Strategy - PaymentStrategy (CreditCard, PayPal, Points)
 * 5. Observer - Order notifica a User sobre cambios de estado
 * 6. Decorator - WarrantyDecorator, GiftWrapDecorator
 * 7. Adapter - AccountingAdapter para sistema legacy
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StoreDatabase store;
    private static FactoryRegistry factoryRegistry;
    private static Cart cart;
    private static User currentUser;
    private static Order currentOrder;
    private static LegacyAccountingSystem legacySystem;
    private static AccountingService accountingService;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                                                        ║");
        System.out.println("║              BIENVENIDO A SHOPTECH                     ║");
        System.out.println("║              E-Commerce Console MVP                    ║");
        System.out.println("║                                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");

        initializeApplication();

        boolean running = true;
        while(running) {
            showMenu();
            int option = getIntInput();
            scanner.nextLine(); // Limpiar buffer
            running = handleOption(option);
        }

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║     ¡Gracias por usar ShopTech! Vuelve pronto          ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");
        scanner.close();
    }

    /**
     * Inicializa todos los componentes de la aplicación
     * - Patrón Singleton: StoreDatabase
     * - Patrón Factory Method: Registro de factories y creación de productos
     */
    private static void initializeApplication() {
        System.out.println("\n🔧 Inicializando sistema...");

        // 1. Singleton - Obtener instancia única de la base de datos
        store = StoreDatabase.INSTANCE;

        // 2. Factory Method - Registrar factories para cada categoría
        factoryRegistry = new FactoryRegistry();
        factoryRegistry.registerFactory("ELECTRONICS", new ElectronicsFactory());
        factoryRegistry.registerFactory("CLOTHING", new ClothingFactory());

        // 3. Crear productos iniciales usando Factory Method
        loadInitialProducts();

        // 4. Inicializar usuario de prueba (Observer)
        currentUser = new User(1, "Juan Pérez", "juan@shoptech.com", "Calle Principal 123, Culiacán");

        // 5. Inicializar carrito vacío
        cart = new Cart();

        // 6. Adapter - Inicializar sistema de contabilidad legacy
        legacySystem = new LegacyAccountingSystem();
        accountingService = new AccountingAdapter(legacySystem);

        System.out.println(" Sistema inicializado correctamente");
        System.out.println(" " + store.getAllProducts().size() + " productos cargados");
        System.out.println(" Usuario: " + currentUser.getName());
        pauseForUser();
    }

    /**
     * Carga productos iniciales usando el Factory Method pattern
     */
    private static void loadInitialProducts() {
        try {
            // Electronics
            Product laptop = factoryRegistry.createProduct("ELECTRONICS",
                    "LAP-001", "Gaming Laptop RTX 4070", 1200.0, 5, "Computers");
            Product phone = factoryRegistry.createProduct("ELECTRONICS",
                    "PHO-001", "Smartphone Galaxy S24", 899.0, 10, "Smartphones");
            Product tablet = factoryRegistry.createProduct("ELECTRONICS",
                    "TAB-001", "iPad Pro 12.9", 1099.0, 7, "Tablets");

            // Clothing
            Product shirt = factoryRegistry.createProduct("CLOTHING",
                    "SHI-001", "Cotton T-Shirt", 25.0, 50, "Apparel");
            Product jeans = factoryRegistry.createProduct("CLOTHING",
                    "JEA-001", "Blue Denim Jeans", 60.0, 30, "Apparel");
            Product jacket = factoryRegistry.createProduct("CLOTHING",
                    "JAC-001", "Leather Jacket", 150.0, 15, "Outerwear");


            // Agregar a la base de datos (Singleton)
            store.addProduct(laptop);
            store.addProduct(phone);
            store.addProduct(tablet);
            store.addProduct(shirt);
            store.addProduct(jeans);
            store.addProduct(jacket);

        } catch(Exception e) {
            System.err.println(" Error cargando productos: " + e.getMessage());
        }
    }

    /**
     * Muestra el menú principal
     */
    private static void showMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║              MENÚ PRINCIPAL - SHOPTECH                 ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");
        System.out.println("  1.  Ver catálogo de productos");
        System.out.println("  2.  Agregar producto al carrito");
        System.out.println("  3.  Ver carrito");
        System.out.println("  4.  Aplicar servicios extra (Decorator)");
        System.out.println("  5.  Proceder al checkout (Builder)");
        System.out.println("  6.  Seleccionar método de pago (Strategy)");
        System.out.println("  7.  Procesar pago");
        System.out.println("  8.  Ver estado de orden");
        System.out.println("  9.  Salir");
        System.out.println("─".repeat(56));
        System.out.print("Selecciona una opción [1-9]: ");
    }

    /**
     * Maneja la opción seleccionada del menú
     */
    private static boolean handleOption(int option) {
        try {
            switch(option) {
                case 1:
                    viewCatalog();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    applyDecorators();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    selectPaymentMethod();
                    break;
                case 7:
                    processPayment();
                    break;
                case 8:
                    viewOrderStatus();
                    break;
                case 9:
                    return false;
                default:
                    System.out.println(" Opción inválida. Por favor selecciona 1-9.");
            }
        } catch(Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
        return true;
    }

    /**
     * OPCIÓN 1: Ver catálogo completo de productos
     * Demuestra: Singleton (acceso a StoreDatabase)
     */
    private static void viewCatalog() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("  ║                           CATÁLOGO DE PRODUCTOS                            ║");
        System.out.println("  ╚════════════════════════════════════════════════════════════════════════════╝");

        List<Product> products = store.getAllProducts();

        if(products.isEmpty()) {
            System.out.println("📦 No hay productos disponibles en este momento");
            pauseForUser();
            return;
        }

        System.out.printf("%-12s %-30s %-12s %-10s %-15s%n",
                "ID", "Nombre", "Precio", "Stock", "Categoría");
        System.out.println("─".repeat(80));

        for(Product product : products) {
            System.out.printf("%-12s %-30s $%-11.2f %-10d %-15s%n",
                    product.getId(),
                    truncate(product.getName(), 30),
                    product.getPrice(),
                    product.getStock(),
                    product.getCategory());
        }

        System.out.println("─".repeat(80));
        System.out.println("Total de productos: " + products.size());
        pauseForUser();
    }

    /**
     * OPCIÓN 2: Agregar producto al carrito
     * Demuestra: Singleton (consulta a StoreDatabase)
     */
    private static void addToCart() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║              AGREGAR PRODUCTO AL CARRITO               ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");

        List<Product> products = store.getAllProducts();

        if(products.isEmpty()) {
            System.out.println(" No hay productos disponibles");
            pauseForUser();
            return;
        }

        // Mostrar productos numerados
        for(int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%2d. %-30s $%-8.2f (Stock: %d)%n",
                    i+1, truncate(p.getName(), 30), p.getPrice(), p.getStock());
        }

        System.out.print("\nSelecciona el número del producto (0 para cancelar): ");
        int choice = getIntInput();
        scanner.nextLine();

        if(choice == 0) {
            System.out.println(" Operación cancelada");
            return;
        }

        if(choice < 1 || choice > products.size()) {
            System.out.println(" Selección inválida");
            pauseForUser();
            return;
        }

        Product selectedProduct = products.get(choice - 1);

        System.out.print("Cantidad a agregar: ");
        int quantity = getIntInput();
        scanner.nextLine();

        if(quantity <= 0) {
            System.out.println(" La cantidad debe ser mayor a 0");
            pauseForUser();
            return;
        }

        if(quantity > selectedProduct.getStock()) {
            System.out.println(" Stock insuficiente. Disponible: " + selectedProduct.getStock());
            pauseForUser();
            return;
        }

        // Agregar al carrito (múltiples unidades)
        for(int i = 0; i < quantity; i++) {
            cart.addProduct(selectedProduct);
        }

        System.out.println( quantity + "x " + selectedProduct.getName() + " agregado(s) al carrito");
        System.out.println(" Total en carrito: $" + cart.getTotal());
        pauseForUser();
    }

    /**
     * OPCIÓN 3: Ver contenido del carrito
     */
    private static void viewCart() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║                    TU CARRITO                          ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");

        List<Product> items = cart.getItems();

        if(items.isEmpty()) {
            System.out.println("🛒 Tu carrito está vacío");
            System.out.println("\n💡 Usa la opción 2 para agregar productos");
            pauseForUser();
            return;
        }

        System.out.printf("%-35s %15s%n", "Producto", "Precio");
        System.out.println("─".repeat(52));

        for(int i = 0; i < items.size(); i++) {
            Product item = items.get(i);
            System.out.printf("%-35s $%14.2f%n",
                    truncate(item.getName(), 35), item.getPrice());
        }

        System.out.println("─".repeat(52));
        System.out.printf("%-35s $%14.2f%n", "TOTAL:", cart.getTotal());
        System.out.println("═".repeat(52));
        System.out.println("Total de items: " + items.size());
        pauseForUser();
    }

    /**
     * OPCIÓN 4: Aplicar servicios adicionales (Decorator Pattern)
     * Demuestra: Decorator - agregar funcionalidad sin modificar el objeto original
     */
    private static void applyDecorators() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║           SERVICIOS ADICIONALES (Decorator)            ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");

        List<Product> items = cart.getItems();

        if(items.isEmpty()) {
            System.out.println("🛒 Tu carrito está vacío");
            System.out.println("\n💡 Agrega productos primero (opción 2)");
            pauseForUser();
            return;
        }

        // Mostrar productos en el carrito
        System.out.println("\nProductos en tu carrito:");
        for(int i = 0; i < items.size(); i++) {
            Product p = items.get(i);
            System.out.printf("%2d. %-35s $%.2f%n",
                    i+1, truncate(p.getName(), 35), p.getPrice());
        }

        System.out.print("\nSelecciona el producto a mejorar (0 para cancelar): ");
        int choice = getIntInput();
        scanner.nextLine();

        if(choice == 0) {
            System.out.println(" Operación cancelada");
            return;
        }

        if(choice < 1 || choice > items.size()) {
            System.out.println(" Selección inválida");
            pauseForUser();
            return;
        }

        Product selectedProduct = items.get(choice - 1);

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║              SERVICIOS DISPONIBLES                     ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");
        System.out.println("  1.  Garantía Extendida 2 años (+$50.00)");
        System.out.println("  2.  Envoltorio de Regalo (+$10.00)");
        System.out.println("  3.️   Ambos servicios (+$60.00)");
        System.out.print("\nSelecciona una opción: ");

        int serviceChoice = getIntInput();
        scanner.nextLine();

        ProductDecorator decoratedProduct = null;
        String serviceName = "";

        switch(serviceChoice) {
            case 1:
                decoratedProduct = new WarrantyDecorator(selectedProduct);
                serviceName = "Garantía Extendida";
                break;
            case 2:
                decoratedProduct = new GiftWrapDecorator(selectedProduct);
                serviceName = "Envoltorio de Regalo";
                break;
            case 3:
                // Decoradores anidados (composición)
                decoratedProduct = new GiftWrapDecorator(
                        new WarrantyDecorator(selectedProduct)
                );
                serviceName = "Garantía + Envoltorio";
                break;
            default:
                System.out.println("Opción inválida");
                pauseForUser();
                return;
        }

        // Reemplazar producto en el carrito
        cart.removeProduct(selectedProduct.getId());
        cart.addProduct(decoratedProduct);

        System.out.println("\n " + serviceName + " aplicado correctamente");
        System.out.println("Precio original: $" + selectedProduct.getPrice());
        System.out.println("Precio nuevo: $" + decoratedProduct.getPrice());
        System.out.println("Total del carrito: $" + cart.getTotal());
        pauseForUser();
    }

    /**
     * OPCIÓN 5: Checkout - Crear orden (Builder Pattern)
     * Demuestra: Builder - construcción compleja de objetos paso a paso
     */
    private static void checkout() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║                    CHECKOUT (Builder)                  ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");

        if(cart.getItems().isEmpty()) {
            System.out.println(" Tu carrito está vacío");
            System.out.println("\n Agrega productos antes de hacer checkout (opción 2)");
            pauseForUser();
            return;
        }

        if(currentOrder != null && currentOrder.getOrderStatus() == Order.Status.PENDING) {
            System.out.println("  Ya tienes una orden pendiente");
            System.out.println("ID: " + currentOrder.getOrderId());
            System.out.println("\n Completa el pago primero (opciones 6 y 7)");
            pauseForUser();
            return;
        }

        // Generar ID único de orden
        String orderId = "ORD-" + System.currentTimeMillis();

        System.out.println("\n Resumen de tu compra:");
        System.out.println("Items: " + cart.getItems().size());
        System.out.println("Total: $" + cart.getTotal());

        // Preguntar por nota de regalo (opcional)
        System.out.print("\n¿Deseas agregar una nota de regalo? (S/N): ");
        String addNote = scanner.nextLine().trim();

        String giftNote = null;
        if(addNote.equalsIgnoreCase("S")) {
            System.out.print("Escribe tu nota: ");
            giftNote = scanner.nextLine();
        }

        // Construir orden usando Builder Pattern
        Order.OrderBuilder builder = new Order.OrderBuilder()
                .setOrderId(orderId)
                .setUser(currentUser)
                .setItems(cart.getItems());

        if(giftNote != null && !giftNote.trim().isEmpty()) {
            builder.setGiftNote(giftNote);
        }

        try {
            currentOrder = builder.build();

            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("  ║            ORDEN CREADA EXITOSAMENTE                   ║");
            System.out.println("  ╚════════════════════════════════════════════════════════╝");
            System.out.println("ID de Orden: " + currentOrder.getOrderId());
            System.out.println("Cliente: " + currentOrder.getUser().getName());
            System.out.println("Email: " + currentOrder.getUser().getEmail());
            System.out.println("Dirección: " + currentOrder.getUser().getShippingAddress());
            System.out.println("Total: $" + currentOrder.getTotalAmount());
            System.out.println("Estado: " + currentOrder.getOrderStatus());

            if(giftNote != null) {
                System.out.println("Nota: " + giftNote);
            }

            System.out.println("\n️  Siguiente paso: Selecciona un método de pago (opción 6)");

        } catch(IllegalStateException e) {
            System.out.println(" Error al crear orden: " + e.getMessage());
        }

        pauseForUser();
    }

    /**
     * OPCIÓN 6: Seleccionar método de pago (Strategy Pattern)
     * Demuestra: Strategy - algoritmos intercambiables en tiempo de ejecución
     */
    private static void selectPaymentMethod() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║           MÉTODO DE PAGO (Strategy Pattern)            ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");

        if(currentOrder == null) {
            System.out.println(" No hay ninguna orden activa");
            System.out.println("\n Crea una orden primero (opción 5)");
            pauseForUser();
            return;
        }

        if(currentOrder.getOrderStatus() != Order.Status.PENDING) {
            System.out.println(" Esta orden ya fue procesada");
            System.out.println("Estado actual: " + currentOrder.getOrderStatus());
            pauseForUser();
            return;
        }

        System.out.println("Orden: " + currentOrder.getOrderId());
        System.out.println("Total a pagar: $" + currentOrder.getTotalAmount());

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║              MÉTODOS DE PAGO DISPONIBLES               ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");
        System.out.println("  1.  Tarjeta de Crédito");
        System.out.println("  2. ️PayPal");
        System.out.println("  3.  Puntos de Recompensa (100 pts = $1)");
        System.out.print("\nSelecciona método de pago: ");

        int choice = getIntInput();
        scanner.nextLine();

        PaymentStrategy strategy = null;

        try {
            switch(choice) {
                case 1:
                    System.out.println("\n TARJETA DE CRÉDITO");
                    System.out.println("─".repeat(40));
                    System.out.print("Número de tarjeta (16 dígitos): ");
                    String cardNumber = scanner.nextLine();
                    System.out.print("Nombre del titular: ");
                    String cardHolder = scanner.nextLine();
                    System.out.print("Fecha de expiración (MM/YY): ");
                    String expDate = scanner.nextLine();

                    strategy = new CreditCardStrategy(cardNumber, cardHolder, expDate);
                    System.out.println(" Tarjeta configurada: ****" + cardNumber.substring(12));
                    break;

                case 2:
                    System.out.println("\n️  PAYPAL");
                    System.out.println("─".repeat(40));
                    System.out.print("Email de PayPal: ");
                    String email = scanner.nextLine();

                    strategy = new PayPalStrategy(email);
                    System.out.println(" PayPal configurado: " + email);
                    break;

                case 3:
                    System.out.println("\n PUNTOS DE RECOMPENSA");
                    System.out.println("─".repeat(40));
                    System.out.println("Monto a pagar: $" + currentOrder.getTotalAmount());
                    int pointsNeeded = (int)(currentOrder.getTotalAmount() * 100);
                    System.out.println("Puntos necesarios: " + pointsNeeded);
                    System.out.print("Puntos disponibles: ");
                    int points = getIntInput();
                    scanner.nextLine();

                    strategy = new PointsStrategy(points);
                    System.out.println(" Puntos configurados: " + points + " pts disponibles");
                    break;

                default:
                    System.out.println(" Opción inválida");
                    pauseForUser();
                    return;
            }

            // Reconstruir orden con el método de pago seleccionado
            currentOrder = new Order.OrderBuilder()
                    .setOrderId(currentOrder.getOrderId())
                    .setUser(currentOrder.getUser())
                    .setItems(currentOrder.getItems())
                    .setPaymentMethod(strategy)
                    .setGiftNote(currentOrder.getGiftNote())
                    .build();

            System.out.println("\n Método de pago configurado correctamente");
            System.out.println("\n️  Siguiente paso: Procesar pago (opción 7)");

        } catch(IllegalArgumentException e) {
            System.out.println(" Error de validación: " + e.getMessage());
        } catch(Exception e) {
            System.out.println(" Error inesperado: " + e.getMessage());
        }

        pauseForUser();
    }

    /**
     * OPCIÓN 7: Procesar pago
     * Demuestra: Strategy (ejecuta algoritmo de pago), Observer (notifica cambios),
     *            Adapter (registra en sistema legacy)
     */
    private static void processPayment() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║                  PROCESAR PAGO                         ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");

        if(currentOrder == null) {
            System.out.println(" No hay ninguna orden activa");
            System.out.println("\n Crea una orden primero (opción 5)");
            pauseForUser();
            return;
        }

        if(currentOrder.getPaymentMethod() == null) {
            System.out.println(" No has seleccionado un método de pago");
            System.out.println("\n Selecciona método de pago primero (opción 6)");
            pauseForUser();
            return;
        }

        if(currentOrder.getOrderStatus() != Order.Status.PENDING) {
            System.out.println(" Esta orden ya fue procesada");
            System.out.println("Estado actual: " + currentOrder.getOrderStatus());
            pauseForUser();
            return;
        }

        System.out.println("Orden: " + currentOrder.getOrderId());
        System.out.println("Total: $" + currentOrder.getTotalAmount());
        System.out.println("\n Procesando pago...");
        System.out.println("═".repeat(56));

        try {
            // STRATEGY PATTERN + OBSERVER PATTERN
            // processPayment() usa la estrategia y notifica a observers
            currentOrder.processPayment();

            System.out.println("═".repeat(56));
            System.out.println("\n ¡PAGO PROCESADO EXITOSAMENTE!");
            System.out.println("Estado de la orden: " + currentOrder.getOrderStatus());

            // ADAPTER PATTERN - Registrar en sistema de contabilidad legacy
            System.out.println("\n Registrando venta en sistema de contabilidad...");
            accountingService.logSale(currentOrder);

            // Limpiar carrito después de compra exitosa
            cart.clear();
            System.out.println("\n🛒 Carrito vaciado");

            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("  ║             ¡GRACIAS POR TU COMPRA!                    ║");
            System.out.println("  ╚════════════════════════════════════════════════════════╝");
            System.out.println("Recibirás un email de confirmación en: " + currentOrder.getUser().getEmail());

        } catch(RuntimeException e) {
            System.out.println("═".repeat(56));
            System.out.println("\n ERROR AL PROCESAR PAGO");
            System.out.println("Motivo: " + e.getMessage());
            System.out.println("\n Verifica tu método de pago e intenta nuevamente");
        }

        pauseForUser();
    }

    /**
     * OPCIÓN 8: Ver estado de la orden actual
     * Demuestra: Observer - la orden ha notificado cambios al usuario
     */
    private static void viewOrderStatus() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║              ESTADO DE ORDEN ACTUAL                    ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝");

        if(currentOrder == null) {
            System.out.println(" No hay ninguna orden activa");
            System.out.println("\n Crea una orden con la opción 5 (Checkout)");
            pauseForUser();
            return;
        }

        System.out.println("\n INFORMACIÓN DE LA ORDEN");
        System.out.println("─".repeat(56));
        System.out.println("ID de Orden: " + currentOrder.getOrderId());
        System.out.println("Estado: " + getStatusEmoji(currentOrder.getOrderStatus()) + " " + currentOrder.getOrderStatus());
        System.out.println("Total: $" + currentOrder.getTotalAmount());

        System.out.println("\n INFORMACIÓN DEL CLIENTE");
        System.out.println("─".repeat(56));
        System.out.println("Nombre: " + currentOrder.getUser().getName());
        System.out.println("Email: " + currentOrder.getUser().getEmail());
        System.out.println("Dirección: " + currentOrder.getUser().getShippingAddress());

        System.out.println("\n PRODUCTOS (" + currentOrder.getItems().size() + " items)");
        System.out.println("─".repeat(56));
        for(Product item : currentOrder.getItems()) {
            System.out.printf("  • %-35s $%.2f%n",
                    truncate(item.getName(), 35), item.getPrice());
        }

        if(currentOrder.getGiftNote() != null) {
            System.out.println("\n NOTA DE REGALO");
            System.out.println("─".repeat(56));
            System.out.println(currentOrder.getGiftNote());
        }

        if(currentOrder.getPaymentMethod() != null) {
            System.out.println("\n MÉTODO DE PAGO");
            System.out.println("─".repeat(56));
            System.out.println("Configurado: ");
        }

        pauseForUser();
    }

    // ═══════════════════════════════════════════════════════════
    //                    MÉTODOS AUXILIARES
    // ═══════════════════════════════════════════════════════════

    /**
     * Lee un entero del scanner con validación
     */
    private static int getIntInput() {
        while(!scanner.hasNextInt()) {
            System.out.print(" Entrada inválida. Ingresa un número: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Pausa la ejecución hasta que el usuario presione Enter
     */
    private static void pauseForUser() {
        System.out.println("\n[Presiona Enter para continuar...]");
        try {
            scanner.nextLine();
        } catch(Exception e) {
            // Ignorar errores de entrada
        }
    }

    /**
     * Trunca un string a la longitud especificada
     */
    private static String truncate(String str, int maxLength) {
        if(str == null) return "";
        if(str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * Retorna un emoji según el estado de la orden
     */
    private static String getStatusEmoji(Order.Status status) {
        switch(status) {
            case PENDING: return "⏳";
            case PAID: return "✅";
            case SHIPPED: return "🚚";
            case DELIVERED: return "📦";
            case CANCELED: return "❌";
            default: return "❓";
        }
    }
}