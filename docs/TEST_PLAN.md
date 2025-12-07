# Plan de Pruebas - ShopTech E-Commerce

> Documentación completa de casos de prueba para validar la implementación de los 7 patrones de diseño en ShopTech E-Commerce Console MVP.

---

## 📋 Resumen de Pruebas

| # | Caso de Prueba | Patrón | Estado |
|---|---------------|--------|--------|
| 1 | Singleton Pattern | Singleton | [✅] |
| 2 | Factory Method Pattern | Factory | [✅] |
| 3 | Builder Pattern | Builder | [✅] |
| 4 | Strategy - Tarjeta de Crédito | Strategy | [✅] |
| 5 | Strategy - PayPal | Strategy | [✅] |
| 6 | Strategy - Puntos (Exitoso) | Strategy | [✅] |
| 7 | Strategy - Puntos (Fallido) | Strategy | [✅] |
| 8 | Observer Pattern | Observer | [✅] |
| 9 | Decorator - Garantía | Decorator | [✅] |
| 10 | Decorator - Envoltorio | Decorator | [✅] |
| 11 | Decorator - Anidados | Decorator | [✅] |
| 12 | Adapter Pattern | Adapter | [✅] |
| 13 | Validación de Stock | Validación | [✅] |
| 14 | Flujo End-to-End | Integración | [✅] |
| 15 | Manejo de Errores | Robustez | [✅] |

---

## 🔹 Caso de Prueba 1: Singleton Pattern

**Objetivo:** Verificar que solo existe una instancia de `StoreDatabase`

### Pasos:
1. Obtener instancia:
   ```java
   StoreDatabase db1 = StoreDatabase.getInstance();
   ```
2. Obtener segunda instancia:
   ```java
   StoreDatabase db2 = StoreDatabase.getInstance();
   ```
3. Comparar referencias:
   ```java
   db1 == db2
   ```

### Resultado Esperado:
- ✅ `true` (misma instancia)

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 🏭 Caso de Prueba 2: Factory Method Pattern

**Objetivo:** Crear productos de diferentes categorías dinámicamente

### Pasos:
1. Ejecutar aplicación
2. Ir a "**Ver catálogo**" (Opción 1)
3. Verificar que aparezcan productos de Electronics, Clothing y Home

### Resultado Esperado:
- ✅ Mínimo 9 productos
- ✅ Categorías: `ELECTRONICS`, `CLOTHING`

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 🔨 Caso de Prueba 3: Builder Pattern

**Objetivo:** Construir una orden compleja con parámetros opcionales

### Pasos:
1. Agregar productos al carrito (Opción 2)
2. Hacer checkout (Opción 5)
3. Agregar nota de regalo cuando se solicite
4. Verificar que la orden se cree con todos los datos

### Resultado Esperado:
- ✅ Orden creada con ID único (formato: `ORD-{timestamp}`)
- ✅ Usuario asignado correctamente
- ✅ Items del carrito incluidos
- ✅ Nota de regalo guardada
- ✅ Total calculado automáticamente
- ✅ Estado inicial: `PENDING`

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 💳 Caso de Prueba 4: Strategy Pattern - Tarjeta de Crédito

**Objetivo:** Pagar con tarjeta de crédito

### Pasos:
1. Crear orden con productos
2. Seleccionar método de pago (Opción 6)
3. Elegir "**Tarjeta de Crédito**"
4. Ingresar datos válidos:
   - Número: `1234567812345678` (16 dígitos)
   - Titular: `Juan Pérez`
   - Expiración: `12/25`
5. Procesar pago (Opción 7)

### Resultado Esperado:
- ✅ Pago procesado exitosamente
- ✅ Estado cambia a `PAID`
- ✅ Usuario recibe notificación
- ✅ Carrito se vacía

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 📧 Caso de Prueba 5: Strategy Pattern - PayPal

**Objetivo:** Pagar con PayPal

### Pasos:
1. Crear nueva orden con productos
2. Seleccionar "**PayPal**" como método de pago
3. Ingresar email: `test@paypal.com`
4. Procesar pago

### Resultado Esperado:
- ✅ Pago exitoso con PayPal
- ✅ Mismo comportamiento que tarjeta de crédito

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## ⭐ Caso de Prueba 6: Strategy Pattern - Puntos (Exitoso)

**Objetivo:** Pagar con puntos suficientes

### Pasos:
1. Crear orden de `$50`
2. Seleccionar "**Puntos de Recompensa**"
3. Ingresar `5000` puntos (`50 * 100`)
4. Procesar pago

### Resultado Esperado:
- ✅ Pago exitoso
- ✅ Mensaje: `"Paid $50.00 using 5000 points"`
- ✅ Puntos restantes mostrados

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## ⛔ Caso de Prueba 7: Strategy Pattern - Puntos (Fallido)

**Objetivo:** Intentar pagar sin puntos suficientes

### Pasos:
1. Crear orden de `$100`
2. Seleccionar "**Puntos de Recompensa**"
3. Ingresar `5000` puntos (solo equivalen a `$50`)
4. Intentar procesar pago

### Resultado Esperado:
- ✅ Pago rechazado
- ✅ Mensaje: `"Insufficient points. Need 10000 but only have 5000. Missing: 5000"`
- ✅ Estado sigue en `PENDING`

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 🔔 Caso de Prueba 8: Observer Pattern

**Objetivo:** Verificar notificaciones automáticas

### Pasos:
1. Crear orden y procesar pago
2. Observar la consola

### Resultado Esperado:
- ✅ Mensaje de notificación al usuario cuando cambia el estado
- ✅ Formato: 
  ```
  User [nombre] notified about order [ID]: Order status changed from PENDING to PAID
  ```

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 🛡️ Caso de Prueba 9: Decorator Pattern - Garantía

**Objetivo:** Agregar garantía extendida a un producto

### Pasos:
1. Agregar producto de `$100` al carrito
2. Aplicar servicios extra (Opción 4)
3. Seleccionar "**Garantía Extendida**"
4. Ver carrito (Opción 3)

### Resultado Esperado:
- ✅ Precio aumenta a `$150` (+$50)
- ✅ Descripción incluye `"Extended Warranty (2 years)"`

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 🎁 Caso de Prueba 10: Decorator Pattern - Envoltorio

**Objetivo:** Agregar envoltorio de regalo

### Pasos:
1. Agregar producto de `$100` al carrito
2. Aplicar "**Envoltorio de Regalo**"
3. Verificar precio y descripción

### Resultado Esperado:
- ✅ Precio aumenta a `$110` (+$10)
- ✅ Descripción incluye `"Gift Wrapped"`

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 🎁🛡️ Caso de Prueba 11: Decorator Pattern - Decoradores Anidados

**Objetivo:** Aplicar múltiples decoradores al mismo producto

### Pasos:
1. Agregar laptop de `$1000` al carrito
2. Aplicar "**Ambos servicios**" (Garantía + Envoltorio)
3. Verificar precio final

### Resultado Esperado:
- ✅ Precio final: `$1060` (`$1000 + $50 + $10`)
- ✅ Descripción incluye ambos servicios

### Cálculo:
```
Base:      $1000.00
Garantía:  +$ 50.00
Envoltorio:+$ 10.00
─────────────────────
Total:     $1060.00
```

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 🔄 Caso de Prueba 12: Adapter Pattern

**Objetivo:** Registrar venta en sistema legacy

### Pasos:
1. Completar una compra exitosa (Opción 7)
2. Observar mensajes en consola

### Resultado Esperado:
- ✅ Mensaje: `"ADAPTER: Translating logSale() -> registrarVenta()"`
- ✅ Mensaje del sistema legacy con datos en español
- ✅ Información de la venta registrada correctamente

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 📦 Caso de Prueba 13: Validación de Stock

**Objetivo:** No permitir agregar más productos que el stock disponible

### Pasos:
1. Ver producto con stock de `5` unidades
2. Intentar agregar `10` unidades al carrito

### Resultado Esperado:
- ✅ Mensaje de error: `"Stock insuficiente"`
- ✅ No se agregan productos al carrito

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 🔄 Caso de Prueba 14: Flujo Completo End-to-End

**Objetivo:** Ejecutar todo el flujo de compra

### Pasos:
1. Ver catálogo (Opción 1)
2. Agregar 3 productos diferentes al carrito (Opción 2)
3. Aplicar garantía a uno de ellos (Opción 4)
4. Ver carrito y verificar total (Opción 3)
5. Hacer checkout con nota de regalo (Opción 5)
6. Seleccionar tarjeta de crédito como pago (Opción 6)
7. Procesar pago (Opción 7)
8. Ver estado de orden (Opción 8)
9. Verificar que todo se registró correctamente

### Resultado Esperado:
- ✅ Flujo completo sin errores
- ✅ Todos los patrones ejecutados correctamente
- ✅ Orden completada y registrada

### Patrones Demostrados:
| Patrón | Componente |
|--------|-----------|
| Singleton | `StoreDatabase` |
| Factory | `ProductFactory`, `FactoryRegistry` |
| Builder | `Order.OrderBuilder` |
| Decorator | `WarrantyDecorator` |
| Strategy | `CreditCardStrategy` |
| Observer | `User` notificado |
| Adapter | `AccountingAdapter` |

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## ⚠️ Caso de Prueba 15: Manejo de Errores

**Objetivo:** Validar manejo robusto de errores

### Pasos a probar:

| # | Escenario | Resultado Esperado |
|---|-----------|-------------------|
| 1 | Checkout con carrito vacío | Error: "Tu carrito está vacío" |
| 2 | Pagar sin seleccionar método | Error: "No has seleccionado un método de pago" |
| 3 | Procesar pago sin tener orden | Error: "No hay ninguna orden activa" |
| 4 | Tarjeta inválida (< 16 dígitos) | Error de validación |
| 5 | Email inválido en PayPal | Error de validación |

### Resultado Esperado:
- ✅ Mensajes de error claros y específicos
- ✅ No crashes de la aplicación
- ✅ Usuario puede continuar después del error

### Estado: 
- [x] Pasó 
- [ ] Falló

---

## 📝 Notas de Ejecución

### Cómo ejecutar las pruebas

1. **Compilar el proyecto**:
   ```bash
   mvn clean compile
   ```

2. **Ejecutar la aplicación**:
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```

3. **Seguir los casos de prueba** en el orden indicado

### Ambiente de Pruebas
- **Java Version:** 17+
- **Build Tool:** Maven
- **OS:** Compatible con Windows, macOS, Linux

---

## ✅ Registro de Resultados

| Fecha | Ejecutado por | Casos Pasados | Casos Fallidos | Observaciones |
|-------|---------------|---------------|----------------|---------------|
| 2025-12-07 | Automated TestRunner | 15/15 | 0/15 | ✅ Todos los patrones validados |

---

## 📚 Referencias

- [README.md](README.md) - Documentación general del proyecto
- [Main.java](src/main/java/org/example/Main.java) - Punto de entrada de la aplicación
- [StoreDatabase.java](src/main/java/org/example/config/StoreDatabase.java) - Singleton Pattern
- [Order.java](src/main/java/org/example/model/order/Order.java) - Builder & Observer Patterns
