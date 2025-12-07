# Diagrama de Clases - ShopTech E-Commerce

## 7 Patrones de Diseño GoF

```mermaid
classDiagram
    direction TB

    %% ==================== SINGLETON ====================
    class StoreDatabase {
        <<Singleton>>
        +INSTANCE
        -products ArrayList~Product~
        -factoryRegistry FactoryRegistry
        +addProduct(Product) void
        +getProduct(String) Product
        +getAllProducts() ArrayList
        +getFactoryRegistry() FactoryRegistry
    }

    %% ==================== PRODUCTS ====================
    class Product {
        <<abstract>>
        -id String
        -name String
        -price double
        -stock int
        -category String
        +getId() String
        +getName() String
        +getPrice() double
        +getStock() int
        +getDetails()* String
        +incrementStock() void
        +decrementStock() void
    }

    class Electronics {
        +Electronics(id, name, price, stock, category)
        +getDetails() String
    }

    class Clothing {
        +Clothing(id, name, price, stock, category)
        +getDetails() String
    }

    %% ==================== FACTORY ====================
    class ProductFactory {
        <<abstract>>
        +createProduct(id, name, price, stock, category)* Product
    }

    class ElectronicsFactory {
        +createProduct() Product
    }

    class ClothingFactory {
        +createProduct() Product
    }

    class FactoryRegistry {
        -factories Map~String, ProductFactory~
        +registerFactory(String, ProductFactory) void
        +createProduct(type, id, name, price, stock, category) Product
        +hasFactory(String) boolean
    }

    %% ==================== ORDER + BUILDER ====================
    class Order {
        <<Subject>>
        -observers List~OrderObserver~
        -orderId String
        -user User
        -items List~Product~
        -totalAmount double
        -orderStatus Status
        -paymentMethod PaymentStrategy
        -giftNote String
        +processPayment() void
        +setStatus(Status) void
        +subscribe(OrderObserver) void
        +unsubscribe(OrderObserver) void
        +notifyObservers(String) void
    }

    class OrderBuilder {
        <<Builder>>
        -orderId String
        -user User
        -items List~Product~
        +setOrderId(String) OrderBuilder
        +setUser(User) OrderBuilder
        +setItems(List) OrderBuilder
        +setPaymentMethod(PaymentStrategy) OrderBuilder
        +setGiftNote(String) OrderBuilder
        +build() Order
    }

    class Status {
        <<enumeration>>
        PENDING
        PAID
        SHIPPED
        DELIVERED
        CANCELED
    }

    class Cart {
        -products List~Product~
        -totalPrice double
        +addProduct(Product) void
        +removeProduct(String) void
        +getTotal() double
        +clear() void
    }

    %% ==================== OBSERVER ====================
    class OrderObserver {
        <<interface>>
        +update(Order, String) void
    }

    class User {
        <<Observer>>
        -id int
        -name String
        -email String
        -shippingAddress String
        +update(Order, String) void
        +getName() String
        +getEmail() String
    }

    %% ==================== STRATEGY ====================
    class PaymentStrategy {
        <<interface>>
        +pay(double) boolean
    }

    class CreditCardStrategy {
        -cardNumber String
        -ownerCard String
        +pay(double) boolean
    }

    class PayPalStrategy {
        -email String
        +pay(double) boolean
    }

    class PointsStrategy {
        -availablePoints int
        +pay(double) boolean
    }

    %% ==================== DECORATOR ====================
    class ProductDecorator {
        <<abstract>>
        #wrappedProduct Product
        +getPrice() double
        +getDetails() String
    }

    class WarrantyDecorator {
        -WARRANTY_COST double
        +getPrice() double
        +getDetails() String
    }

    class GiftWrapDecorator {
        -GIFT_WRAP_COST double
        +getPrice() double
        +getDetails() String
    }

    %% ==================== ADAPTER ====================
    class AccountingService {
        <<interface>>
        +logSale(Order) void
    }

    class LegacyAccountingSystem {
        <<Adaptee>>
        -recordedSales List
        +registrarVenta(String, String, double) void
    }

    class AccountingAdapter {
        <<Adapter>>
        -legacySystem LegacyAccountingSystem
        +logSale(Order) void
    }

    %% ==================== RELACIONES ====================
    
    %% Herencia de productos
    Product <|-- Electronics
    Product <|-- Clothing
    Product <|-- ProductDecorator

    %% Factory Method
    ProductFactory <|-- ElectronicsFactory
    ProductFactory <|-- ClothingFactory
    FactoryRegistry o-- ProductFactory
    ElectronicsFactory ..> Electronics : creates
    ClothingFactory ..> Clothing : creates

    %% Singleton
    StoreDatabase *-- Product
    StoreDatabase *-- FactoryRegistry

    %% Builder
    OrderBuilder ..> Order : builds
    Order *-- Status

    %% Observer
    OrderObserver <|.. User
    Order o-- OrderObserver
    Order *-- User

    %% Strategy
    PaymentStrategy <|.. CreditCardStrategy
    PaymentStrategy <|.. PayPalStrategy
    PaymentStrategy <|.. PointsStrategy
    Order o-- PaymentStrategy

    %% Decorator
    ProductDecorator <|-- WarrantyDecorator
    ProductDecorator <|-- GiftWrapDecorator
    ProductDecorator o-- Product : wraps

    %% Adapter
    AccountingService <|.. AccountingAdapter
    AccountingAdapter o-- LegacyAccountingSystem

    %% Cart y Order
    Cart o-- Product
    Order o-- Product
```

## Resumen de Patrones

| Patron | Tipo | Clases Involucradas |
|--------|------|---------------------|
| **Singleton** | Creacional | `StoreDatabase` |
| **Factory Method** | Creacional | `ProductFactory`, `ElectronicsFactory`, `ClothingFactory`, `FactoryRegistry` |
| **Builder** | Creacional | `Order.OrderBuilder`, `Order` |
| **Strategy** | Comportamiento | `PaymentStrategy`, `CreditCardStrategy`, `PayPalStrategy`, `PointsStrategy` |
| **Observer** | Comportamiento | `Order` (Subject), `OrderObserver`, `User` (Observer) |
| **Decorator** | Estructural | `ProductDecorator`, `WarrantyDecorator`, `GiftWrapDecorator` |
| **Adapter** | Estructural | `AccountingService` (Target), `AccountingAdapter`, `LegacyAccountingSystem` (Adaptee) |
