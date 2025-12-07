# ShopTech - E-Commerce Console MVP

Sistema de comercio electrónico desarrollado en consola que demuestra la implementación de 7 patrones de diseño del Gang of Four (GoF).

## 🎯 Objetivo del Proyecto

Aplicación académica que simula un e-commerce completo implementando patrones de diseño fundamentales para demostrar buenas prácticas de arquitectura de software.

## 🏗️ Arquitectura y Patrones de Diseño

### 1. **Singleton Pattern**
- **Clase:** `StoreDatabase`
- **Propósito:** Garantizar una única instancia del inventario global
- **Implementación:** Constructor privado con método `getInstance()`

### 2. **Factory Method Pattern**
- **Clases:** `ProductFactory`, `ElectronicsFactory`, `ClothingFactory`
- **Propósito:** Creación dinámica de productos por categoría
- **Implementación:** Factories específicas registradas en `FactoryRegistry`

### 3. **Builder Pattern**
- **Clase:** `Order.OrderBuilder`
- **Propósito:** Construcción de órdenes complejas con parámetros opcionales
- **Implementación:** Clase interna estática con métodos encadenables

### 4. **Strategy Pattern**
- **Interfaz:** `PaymentStrategy`
- **Implementaciones:** `CreditCardStrategy`, `PayPalStrategy`, `PointsStrategy`
- **Propósito:** Algoritmos de pago intercambiables en tiempo de ejecución

### 5. **Observer Pattern**
- **Subject:** `Order`
- **Observer:** `User` (implementa `OrderObserver`)
- **Propósito:** Notificaciones automáticas cuando cambia el estado de la orden

### 6. **Decorator Pattern**
- **Clase base:** `ProductDecorator`
- **Decoradores:** `WarrantyDecorator`, `GiftWrapDecorator`
- **Propósito:** Agregar funcionalidades a productos sin modificar la clase original

### 7. **Adapter Pattern**
- **Interfaz:** `AccountingService`
- **Adaptee:** `LegacyAccountingSystem`
- **Adapter:** `AccountingAdapter`
- **Propósito:** Integrar sistema de contabilidad legacy con interfaz incompatible

## 📁 Estructura del Proyecto
```
src/
└── main/
    └── java/
        └── org/
            └── example/
                ├── Main.java                        # Punto de entrada
                ├── TestRunner.java                  # Suite de pruebas
                ├── config/
                │   ├── ConfigurationManager.java
                │   └── StoreDatabase.java           # Singleton
                ├── model/
                │   ├── product/
                │   │   ├── Product.java             # Clase abstracta
                │   │   ├── Electronics.java
                │   │   └── Clothing.java
                │   └── order/
                │       ├── Order.java               # Builder + Subject
                │       ├── OrderObserver.java       # Interfaz Observer
                │       ├── User.java                # Observer concreto
                │       └── Cart.java
                ├── patterns/
                │   ├── factory/
                │   │   ├── ProductFactory.java      # Factory abstracta
                │   │   ├── ElectronicsFactory.java
                │   │   ├── ClothingFactory.java
                │   │   └── FactoryRegistry.java
                │   ├── strategy/
                │   │   ├── PaymentStrategy.java     # Interfaz Strategy
                │   │   ├── CreditCardStrategy.java
                │   │   ├── PayPalStrategy.java
                │   │   └── PointsStrategy.java
                │   ├── decorator/
                │   │   ├── ProductDecorator.java    # Decorator abstracto
                │   │   ├── WarrantyDecorator.java
                │   │   └── GiftWrapDecorator.java
                │   └── adapter/
                │       ├── AccountingService.java   # Target
                │       ├── LegacyAccountingSystem.java  # Adaptee
                │       └── AccountingAdapter.java   # Adapter
                └── service/
                    └── NotificationService.java
```


## 🚀 Cómo Ejecutar

### Requisitos
- Java 8 o superior
- IDE (IntelliJ IDEA, Eclipse, VS Code) o terminal

### Pasos
1. Clonar el repositorio
```bash
git clone [tu-repo]
cd shoptech
```

2. Compilar
```bash
javac -d bin src/org/example/**/*.java
```

3. Ejecutar
```bash
java -cp bin org.example.Main
```

## 📖 Guía de Uso

### Flujo de Compra Típico

1. **Ver Catálogo** (Opción 1)
   - Visualiza todos los productos disponibles

2. **Agregar al Carrito** (Opción 2)
   - Selecciona productos y cantidad

3. **Aplicar Servicios Extra** (Opción 4) - Opcional
   - Agrega garantía (+$50) o envoltorio (+$10)

4. **Checkout** (Opción 5)
   - Crea la orden con Builder Pattern

5. **Seleccionar Pago** (Opción 6)
   - Elige: Tarjeta, PayPal o Puntos

6. **Procesar Pago** (Opción 7)
   - Ejecuta el pago y registra en contabilidad

7. **Ver Estado** (Opción 8)
   - Consulta detalles de la orden

## 🧪 Testing

Ver `TEST_PLAN.md` para casos de prueba detallados.

### Casos de Prueba Principales
- ✅ Singleton: Una sola instancia de BD
- ✅ Factory: Creación dinámica de productos
- ✅ Builder: Órdenes con parámetros opcionales
- ✅ Strategy: Cambio de método de pago
- ✅ Observer: Notificaciones automáticas
- ✅ Decorator: Servicios adicionales anidables
- ✅ Adapter: Integración con sistema legacy

## 📚 Principios SOLID Aplicados

### SRP (Single Responsibility Principle)
- Cada clase tiene una única responsabilidad
- `Order` gestiona datos, `PaymentStrategy` procesa pagos

### OCP (Open/Closed Principle)
- Nuevos productos: Crear nueva factory sin modificar código
- Nuevos pagos: Crear nueva estrategia sin modificar Order

### LSP (Liskov Substitution Principle)
- Cualquier `Product` puede usarse donde se espere `IProduct`
- Decoradores pueden reemplazar productos sin romper funcionalidad

### ISP (Interface Segregation Principle)
- Interfaces pequeñas y específicas (`PaymentStrategy`, `OrderObserver`)

### DIP (Dependency Inversion Principle)
- Dependencia de abstracciones (`IProduct`, `PaymentStrategy`)
- No dependencia de clases concretas

## 🎓 Lecciones Aprendidas

[Documenta aquí tus aprendizajes durante el desarrollo]

## 👥 Autor
Marco Vinicio Palazuelos Leon

7 de Diciembre del 2025

## 📄 Licencia

Proyecto académico - [Tu universidad/institución]
