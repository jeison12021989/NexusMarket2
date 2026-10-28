# NexusMarket

API REST de un marketplace que intermedia entre compradores y vendedores:
registro de usuarios, catálogo, inventario distribuido en bodegas, carrito,
ciclo completo del pedido, facturación, envíos, devoluciones, reembolsos y
reportes administrativos.

Proyecto de **Construcción de Software II** (2026-2). Java 17, Spring Boot,
Spring Data JPA y MySQL.

## Arquitectura por capas

| Paquete | Responsabilidad |
|---|---|
| `application.controller` | Endpoints REST, delegan en los servicios |
| `application.service` | Casos de uso y reglas de negocio (interfaz + implementación, `@Transactional`) |
| `application.repository` | Acceso a datos con Spring Data JPA |
| `application.domain.models` | Entidades JPA y su comportamiento |
| `application.exception` | `BusinessRuleException` y manejador global (respuesta 400 en JSON) |
| `application.config` | Configuración de seguridad |

La herencia (`User`, `Product`, `Warehouse`) se persiste con una tabla por
jerarquía y una columna discriminadora.

## Requisitos

- JDK 17 o superior
- MySQL 8 en `localhost:3306` (la base `nexusmarket_db` se crea sola)

## Configuración

La contraseña de MySQL **no está en el repositorio**: se lee de la variable de
entorno `DB_PASSWORD` (usuario `root`).

```powershell
$env:DB_PASSWORD = "tu-contraseña"
```

## Ejecutar

```powershell
.\mvnw.cmd clean test            # pruebas de integración contra MySQL
.\mvnw.cmd spring-boot:run       # API en http://localhost:8080
```

Con la API corriendo, en otra terminal, el script `demo.ps1` recorre todo el
flujo de negocio (vendedor, producto, bodega, inventario, carrito, pedido,
pago, despacho, entrega, factura, devolución, reembolso, reporte y un error de
regla de negocio):

```powershell
powershell -ExecutionPolicy Bypass -File .\demo.ps1
```

## Endpoints

| Recurso | Endpoints |
|---|---|
| Vendedores | `POST /api/sellers` · `GET /api/sellers/{id}` |
| Compradores | `POST /api/buyers` · `GET /api/buyers/{id}` |
| Productos | `POST /api/products/physical` · `POST /api/products/digital` · `POST /api/products/{id}/variants` |
| Bodegas e inventario | `POST /api/warehouses` · `POST /api/warehouses/{id}/inventory` · `POST /api/warehouses/{id}/inventory/reserve` |
| Carrito | `GET /api/buyers/{buyerId}/cart` · `POST /api/buyers/{buyerId}/cart/items` · `GET /api/buyers/{buyerId}/cart/total` |
| Pedidos | `POST /api/orders/checkout` · `GET /api/orders/{id}` · `POST /api/orders/{id}/pay` · `/dispatch` · `/deliver` · `/complete` · `/invoice` |
| Devoluciones | `POST /api/returns` · `POST /api/returns/{id}/approve` · `POST /api/returns/{id}/refund` |
| Reportes | `POST /api/reports` |

## Reglas de negocio implementadas

- El inventario nunca es negativo y no se reserva inventario dañado.
- El pedido sigue el ciclo Carrito, Pendiente de pago, Pagado, Despachado,
  Entregado, Finalizado; un pedido finalizado no se modifica.
- El reembolso exige una devolución aprobada.
- Cada usuario tiene un único rol (herencia con discriminador).
- Un producto físico requiere envío; uno digital no.

## Alcance y mejoras pendientes

- La autenticación por roles (RG-01 y RG-03) queda fuera de esta entrega: los
  endpoints están abiertos. Siguiente paso: Spring Security con roles.
- Pendiente: DTOs con validación, unicidad de correo y documento, y reservar
  inventario dentro del checkout.
