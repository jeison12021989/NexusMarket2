package application.controller;

import application.domain.models.SellerWarehouse;
import application.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public SellerWarehouse create(@RequestBody Map<String, Object> body) {
        return warehouseService.createSellerWarehouse(
                Long.valueOf(body.get("sellerId").toString()),
                body.get("name").toString(),
                body.get("location").toString());
    }

    @PostMapping("/{id}/inventory")
    public void registerInventory(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        warehouseService.registerInventory(
                id,
                Long.valueOf(body.get("productId").toString()),
                Integer.parseInt(body.get("quantity").toString()));
    }

    @PostMapping("/{id}/inventory/reserve")
    public boolean reserveStock(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        return warehouseService.reserveStock(
                id,
                Long.valueOf(body.get("productId").toString()),
                Integer.parseInt(body.get("quantity").toString()));
    }
}
