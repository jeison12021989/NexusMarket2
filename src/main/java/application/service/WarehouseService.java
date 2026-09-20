package application.service;

import application.domain.models.SellerWarehouse;

/**
 * Domain 4 - Warehouse Management and Domain 6 - Inventory Management.
 */
public interface WarehouseService {

    SellerWarehouse createSellerWarehouse(Long sellerId, String name, String location);

    void registerInventory(Long warehouseId, Long productId, int quantity);

    boolean reserveStock(Long warehouseId, Long productId, int quantity);
}
