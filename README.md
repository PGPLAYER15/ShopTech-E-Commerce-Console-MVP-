# 🛒 ShopTech: E-commerce Architecture MVP

> Un sistema de comercio electrónico de consola diseñado para demostrar la aplicación práctica de **Patrones de Diseño** y principios **SOLID** en un entorno de desarrollo ágil.

## 📋 Descripción

**ShopTech** no es solo una tienda virtual; es un ejercicio de arquitectura de software. El objetivo de este proyecto es resolver problemas comunes de desarrollo (gestión de estados, creación de objetos complejos, algoritmos intercambiables) mediante soluciones elegantes y desacopladas.

El sistema simula el flujo completo de una compra: desde la selección de productos y gestión del carrito, hasta la construcción de órdenes complejas y procesamiento de pagos con notificaciones en tiempo real.

## 🚀 Funcionalidades Core

* **Catálogo Centralizado:** Gestión de inventario único en memoria.
* **Carrito de Compras:** Lógica de adición y cálculo de subtotales.
* **Checkout Flexible:** Creación de órdenes con múltiples atributos opcionales (regalo, direcciones).
* **Sistema de Pagos:** Soporte para múltiples pasarelas (PayPal, Tarjeta de Crédito).
* **Notificaciones:** Sistema reactivo que avisa al usuario sobre cambios de estado.
* **Integración Legacy:** Adaptador para conectar con sistemas contables antiguos.

## 🏗️ Arquitectura y Patrones de Diseño

Este proyecto implementa **7 Patrones de Diseño** clave para garantizar escalabilidad y mantenibilidad:

| Patrón | Tipo | Uso en ShopTech |
| :--- | :--- | :--- |
| **Singleton** | Creacional | Garantiza una única instancia de la Base de Datos (`StoreDatabase`). |
| **Factory Method** | Creacional | Centraliza la creación de productos (`Electronics`, `Clothing`) sin acoplar el cliente. |
| **Builder** | Creacional | Construye objetos `Order` complejos paso a paso, evitando constructores telescópicos. |
| **Strategy** | Comportamiento | Permite cambiar el algoritmo de pago (`PayPal` vs `CreditCard`) en tiempo de ejecución. |
| **Observer** | Comportamiento | Notifica a los usuarios (`Observer`) automáticamente cuando su pedido cambia de estado. |
| **Decorator** | Estructural | Añade responsabilidades a productos (Garantía, Envoltorio) dinámicamente. |
| **Adapter** | Estructural | Permite que el sistema moderno interactúe con una clase de contabilidad heredada (`LegacySystem`). |

## 🛠️ Tecnologías y Principios

* **Lenguaje:** Java 17+
* **Principios:** SOLID (Single Responsibility, Open/Closed, Liskov, Interface Segregation, Dependency Inversion).
* **Interfaz:** Consola (CLI).

## 📂 Estructura del Proyecto

```text
src/
└── main/
    └── java/
        └── org/
            └── example/
                ├── Main.java
                ├── config/
                │   └── StoreDatabase.java
                ├── model/
                │   ├── product/
                │   │   ├── Product.java
                │   │   ├── Electronics.java
                │   │   └── Clothing.java
                │   └── order/
                │       ├── Order.java
                │       ├── Cart.java
                │       └── User.java
                ├── patterns/
                │   ├── factory/
                │   │   └── ProductFactory.java
                │   ├── builder/
                │   │   └── OrderBuilder.java
                │   ├── strategy/
                │   │   ├── PaymentStrategy.java
                │   │   ├── CreditCardStrategy.java
                │   │   └── PayPalStrategy.java
                │   ├── decorator/
                │   │   ├── ProductDecorator.java
                │   │   └── WarrantyDecorator.java
                │   └── adapter/
                │       ├── AccountingService.java
                │       ├── LegacyAccountingSystem.java
                │       └── AccountingAdapter.java
                └── service/
                    └── NotificationService.java
```

