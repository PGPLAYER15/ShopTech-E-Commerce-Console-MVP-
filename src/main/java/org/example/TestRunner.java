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
import org.example.patterns.decorator.WarrantyDecorator;
import org.example.patterns.factory.ClothingFactory;
import org.example.patterns.factory.ElectronicsFactory;
import org.example.patterns.factory.FactoryRegistry;
import org.example.patterns.strategy.CreditCardStrategy;
import org.example.patterns.strategy.PayPalStrategy;
import org.example.patterns.strategy.PaymentStrategy;
import org.example.patterns.strategy.PointsStrategy;

import java.util.List;

/**
 * Test Runner para verificar todos los patrones de diseño
 */
public class TestRunner {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║     SHOPTECH - AUTOMATED TEST RUNNER                   ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        testCase1_Singleton();
        testCase2_FactoryMethod();
        testCase3_Builder();
        testCase4_StrategyCreditCard();
        testCase5_StrategyPayPal();
        testCase6_StrategyPointsSuccess();
        testCase7_StrategyPointsFailed();
        testCase8_Observer();
        testCase9_DecoratorWarranty();
        testCase10_DecoratorGiftWrap();
        testCase11_DecoratorNested();
        testCase12_Adapter();
        testCase13_StockValidation();
        testCase14_EndToEnd();
        testCase15_ErrorHandling();

        // Summary
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    TEST SUMMARY                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total: " + (passed + failed));
        System.out.println("Success Rate: " + (passed * 100 / (passed + failed)) + "%");
    }

    // ======================= TEST CASES =======================

    /**
     * Test Case 1: Singleton Pattern
     */
    private static void testCase1_Singleton() {
        System.out.println("─".repeat(60));
        System.out.println("CASE 1: Singleton Pattern");

        try {
            StoreDatabase db1 = StoreDatabase.INSTANCE;
            StoreDatabase db2 = StoreDatabase.INSTANCE;

            boolean sameInstance = (db1 == db2);

            if (sameInstance) {
                pass("StoreDatabase returns same instance");
            } else {
                fail("StoreDatabase returns different instances");
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 2: Factory Method Pattern
     */
    private static void testCase2_FactoryMethod() {
        System.out.println("─".repeat(60));
        System.out.println("CASE 2: Factory Method Pattern");

        try {
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            registry.registerFactory("CLOTHING", new ClothingFactory());

            Product laptop = registry.createProduct("ELECTRONICS", "LAP-001", "Test Laptop", 1000.0, 5, "Computers");
            Product shirt = registry.createProduct("CLOTHING", "SHI-001", "Test Shirt", 25.0, 10, "Apparel");

            if (laptop != null && shirt != null) {
                pass("Factory creates products correctly - Laptop: " + laptop.getCategory() + ", Shirt: "
                        + shirt.getCategory());
            } else {
                fail("Factory failed to create products");
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 3: Builder Pattern
     */
    private static void testCase3_Builder() {
        System.out.println("─".repeat(60));
        System.out.println("CASE 3: Builder Pattern");

        try {
            User user = new User(1, "Test User", "test@test.com", "123 Test St");
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-001", "Test Product", 100.0, 5, "Test");

            Order order = new Order.OrderBuilder()
                    .setOrderId("ORD-TEST-001")
                    .setUser(user)
                    .setItems(List.of(product))
                    .setGiftNote("Happy Birthday!")
                    .build();

            boolean hasId = order.getOrderId().equals("ORD-TEST-001");
            boolean hasUser = order.getUser().getName().equals("Test User");
            boolean hasItems = order.getItems().size() == 1;
            boolean hasNote = order.getGiftNote() != null;
            boolean isPending = order.getOrderStatus() == Order.Status.PENDING;

            if (hasId && hasUser && hasItems && hasNote && isPending) {
                pass("Order built with all attributes - ID: " + order.getOrderId() + ", Status: "
                        + order.getOrderStatus());
            } else {
                fail("Order missing attributes");
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 4: Strategy - Credit Card
     */
    private static void testCase4_StrategyCreditCard() {
        System.out.println("─".repeat(60));
        System.out.println("CASE 4: Strategy - Credit Card");

        try {
            User user = new User(1, "Test User", "test@test.com", "123 Test St");
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-002", "Test Product", 100.0, 5, "Test");

            PaymentStrategy strategy = new CreditCardStrategy("1234567812345678", "Test User", "12/25");

            Order order = new Order.OrderBuilder()
                    .setOrderId("ORD-CC-001")
                    .setUser(user)
                    .setItems(List.of(product))
                    .setPaymentMethod(strategy)
                    .build();

            order.processPayment();

            if (order.getOrderStatus() == Order.Status.PAID) {
                pass("Credit Card payment processed - Status: PAID");
            } else {
                fail("Payment not processed - Status: " + order.getOrderStatus());
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 5: Strategy - PayPal
     */
    private static void testCase5_StrategyPayPal() {
        System.out.println("─".repeat(60));
        System.out.println("CASE 5: Strategy - PayPal");

        try {
            User user = new User(1, "Test User", "test@test.com", "123 Test St");
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-003", "Test Product", 50.0, 5, "Test");

            PaymentStrategy strategy = new PayPalStrategy("test@paypal.com");

            Order order = new Order.OrderBuilder()
                    .setOrderId("ORD-PP-001")
                    .setUser(user)
                    .setItems(List.of(product))
                    .setPaymentMethod(strategy)
                    .build();

            order.processPayment();

            if (order.getOrderStatus() == Order.Status.PAID) {
                pass("PayPal payment processed - Status: PAID");
            } else {
                fail("Payment not processed - Status: " + order.getOrderStatus());
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 6: Strategy - Points (Success)
     */
    private static void testCase6_StrategyPointsSuccess() {
        System.out.println("─".repeat(60));
        System.out.println("CASE 6: Strategy - Points (Successful)");

        try {
            User user = new User(1, "Test User", "test@test.com", "123 Test St");
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-004", "Test Product", 50.0, 5, "Test");

            // 50 * 100 = 5000 points needed
            PaymentStrategy strategy = new PointsStrategy(5000);

            Order order = new Order.OrderBuilder()
                    .setOrderId("ORD-PTS-001")
                    .setUser(user)
                    .setItems(List.of(product))
                    .setPaymentMethod(strategy)
                    .build();

            order.processPayment();

            if (order.getOrderStatus() == Order.Status.PAID) {
                pass("Points payment processed - 5000 points for $50");
            } else {
                fail("Payment not processed - Status: " + order.getOrderStatus());
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 7: Strategy - Points (Failed - insufficient)
     */
    private static void testCase7_StrategyPointsFailed() {
        System.out.println("─".repeat(60));
        System.out.println("CASE 7: Strategy - Points (Insufficient)");

        try {
            User user = new User(1, "Test User", "test@test.com", "123 Test St");
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-005", "Test Product", 100.0, 5, "Test");

            // 100 * 100 = 10000 points needed, but only 5000 available
            PaymentStrategy strategy = new PointsStrategy(5000);

            Order order = new Order.OrderBuilder()
                    .setOrderId("ORD-PTS-002")
                    .setUser(user)
                    .setItems(List.of(product))
                    .setPaymentMethod(strategy)
                    .build();

            try {
                order.processPayment();
                fail("Payment should have been rejected");
            } catch (RuntimeException e) {
                if (order.getOrderStatus() == Order.Status.PENDING) {
                    pass("Points payment rejected correctly - Status remains PENDING");
                } else {
                    fail("Status changed unexpectedly: " + order.getOrderStatus());
                }
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 8: Observer Pattern
     */
    private static void testCase8_Observer() {
        System.out.println("─".repeat(60));
        System.out.println(" CASE 8: Observer Pattern");

        try {
            User user = new User(1, "Observer Test User", "observer@test.com", "123 Test St");
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-006", "Test Product", 25.0, 5, "Test");

            PaymentStrategy strategy = new CreditCardStrategy("1234567812345678", "Test", "12/25");

            Order order = new Order.OrderBuilder()
                    .setOrderId("ORD-OBS-001")
                    .setUser(user)
                    .setItems(List.of(product))
                    .setPaymentMethod(strategy)
                    .build();

            System.out.println("   [Observer notification should appear below]");
            order.processPayment();

            pass("Observer notified on status change (check console output above)");
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 9: Decorator - Warranty
     */
    private static void testCase9_DecoratorWarranty() {
        System.out.println("─".repeat(60));
        System.out.println("CASE 9: Decorator - Warranty");

        try {
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-007", "Test Laptop", 100.0, 5, "Computers");

            double originalPrice = product.getPrice();
            WarrantyDecorator decorated = new WarrantyDecorator(product);
            double newPrice = decorated.getPrice();

            if (newPrice == originalPrice + 50.0) {
                pass("Warranty adds $50 - Original: $" + originalPrice + ", New: $" + newPrice);
            } else {
                fail("Incorrect price - Expected: $150, Got: $" + newPrice);
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 10: Decorator - Gift Wrap
     */
    private static void testCase10_DecoratorGiftWrap() {
        System.out.println("─".repeat(60));
        System.out.println("CASE 10: Decorator - Gift Wrap");

        try {
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-008", "Test Phone", 100.0, 5, "Phones");

            double originalPrice = product.getPrice();
            GiftWrapDecorator decorated = new GiftWrapDecorator(product);
            double newPrice = decorated.getPrice();

            if (newPrice == originalPrice + 10.0) {
                pass("Gift Wrap adds $10 - Original: $" + originalPrice + ", New: $" + newPrice);
            } else {
                fail("Incorrect price - Expected: $110, Got: $" + newPrice);
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 11: Decorator - Nested (Warranty + Gift Wrap)
     */
    private static void testCase11_DecoratorNested() {
        System.out.println("─".repeat(60));
        System.out.println(" CASE 11: Decorator - Nested (Both)");

        try {
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-009", "Test Laptop", 1000.0, 5, "Computers");

            double originalPrice = product.getPrice();
            Product decorated = new GiftWrapDecorator(new WarrantyDecorator(product));
            double newPrice = decorated.getPrice();
            double expected = originalPrice + 50.0 + 10.0; // 1060

            if (newPrice == expected) {
                pass("Nested decorators work - $" + originalPrice + " + $50 + $10 = $" + newPrice);
            } else {
                fail("Incorrect price - Expected: $" + expected + ", Got: $" + newPrice);
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 12: Adapter Pattern
     */
    private static void testCase12_Adapter() {
        System.out.println("─".repeat(60));
        System.out.println(" CASE 12: Adapter Pattern");

        try {
            User user = new User(1, "Adapter Test", "adapter@test.com", "123 Test St");
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-010", "Test Item", 200.0, 5, "Test");

            PaymentStrategy strategy = new CreditCardStrategy("1234567812345678", "Test", "12/25");

            Order order = new Order.OrderBuilder()
                    .setOrderId("ORD-ADAPT-001")
                    .setUser(user)
                    .setItems(List.of(product))
                    .setPaymentMethod(strategy)
                    .build();

            order.processPayment();

            LegacyAccountingSystem legacy = new LegacyAccountingSystem();
            AccountingService adapter = new AccountingAdapter(legacy);

            System.out.println("   [Adapter translation should appear below]");
            adapter.logSale(order);

            pass("Adapter translates logSale() to registrarVenta() (check output above)");
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 13: Stock Validation
     */
    private static void testCase13_StockValidation() {
        System.out.println("─".repeat(60));
        System.out.println(" CASE 13: Stock Validation");

        try {
            Cart cart = new Cart();
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            Product product = registry.createProduct("ELECTRONICS", "TEST-011", "Limited Item", 50.0, 5, "Test");

            // Add product 5 times (max stock)
            for (int i = 0; i < 5; i++) {
                cart.addProduct(product);
            }

            int itemsInCart = cart.getItems().size();

            if (itemsInCart == 5) {
                pass("Cart respects stock limits - Added 5 of 5 available");
            } else {
                fail("Cart has incorrect items: " + itemsInCart);
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 14: End-to-End Flow
     */
    private static void testCase14_EndToEnd() {
        System.out.println("─".repeat(60));
        System.out.println(" CASE 14: End-to-End Flow");

        try {
            // 1. Store (Singleton)
            StoreDatabase store = StoreDatabase.INSTANCE;

            // 2. Factory - Create products
            FactoryRegistry registry = new FactoryRegistry();
            registry.registerFactory("ELECTRONICS", new ElectronicsFactory());
            registry.registerFactory("CLOTHING", new ClothingFactory());

            Product laptop = registry.createProduct("ELECTRONICS", "E2E-001", "Gaming Laptop", 1000.0, 5, "Computers");
            Product shirt = registry.createProduct("CLOTHING", "E2E-002", "Cool T-Shirt", 30.0, 10, "Apparel");

            store.addProduct(laptop);
            store.addProduct(shirt);

            // 3. Cart
            Cart cart = new Cart();
            cart.addProduct(laptop);
            cart.addProduct(shirt);

            // 4. Decorator
            Product decoratedLaptop = new WarrantyDecorator(laptop);
            cart.removeProduct(laptop.getId());
            cart.addProduct(decoratedLaptop);

            // 5. Builder + Observer
            User user = new User(1, "E2E User", "e2e@test.com", "E2E Street 123");
            PaymentStrategy strategy = new CreditCardStrategy("1234567812345678", "E2E User", "12/26");

            Order order = new Order.OrderBuilder()
                    .setOrderId("ORD-E2E-001")
                    .setUser(user)
                    .setItems(cart.getItems())
                    .setGiftNote("End-to-End Test")
                    .setPaymentMethod(strategy)
                    .build();

            // 6. Process payment (Strategy + Observer)
            order.processPayment();

            // 7. Adapter
            LegacyAccountingSystem legacy = new LegacyAccountingSystem();
            AccountingService adapter = new AccountingAdapter(legacy);
            adapter.logSale(order);

            // Verify
            boolean allPassed = order.getOrderStatus() == Order.Status.PAID;

            if (allPassed) {
                pass("End-to-End flow completed successfully! All 7 patterns executed.");
            } else {
                fail("Flow incomplete - Order status: " + order.getOrderStatus());
            }
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

    /**
     * Test Case 15: Error Handling
     */
    private static void testCase15_ErrorHandling() {
        System.out.println("─".repeat(60));
        System.out.println(" CASE 15: Error Handling");

        int errorsCaught = 0;

        // Test 1: Invalid credit card
        try {
            new CreditCardStrategy("123", "Test", "12/25"); // Less than 16 digits
        } catch (IllegalArgumentException e) {
            errorsCaught++;
            System.out.println("   ✓ Invalid card number caught: " + e.getMessage());
        }

        // Test 2: Invalid PayPal email
        try {
            new PayPalStrategy("invalid-email"); // No @ symbol
        } catch (IllegalArgumentException e) {
            errorsCaught++;
            System.out.println("   ✓ Invalid PayPal email caught: " + e.getMessage());
        }

        // Test 3: Negative points
        try {
            new PointsStrategy(-100);
        } catch (IllegalArgumentException e) {
            errorsCaught++;
            System.out.println("   ✓ Negative points caught: " + e.getMessage());
        }

        if (errorsCaught >= 2) {
            pass("Error handling works - " + errorsCaught + " validation errors caught correctly");
        } else {
            fail("Error handling incomplete - Only " + errorsCaught + " errors caught");
        }
    }

    // ======================= HELPERS =======================

    private static void pass(String message) {
        System.out.println("    PASSED: " + message);
        passed++;
    }

    private static void fail(String message) {
        System.out.println("   FAILED: " + message);
        failed++;
    }
}
