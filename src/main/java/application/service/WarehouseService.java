package application.service;

import application.domain.models.SellerWarehouse;

public interface WarehouseService {

    SellerWarehouse createSellerWarehouse(Long sellerId, String name, String location);

    void registerInventory(Long warehouseId, Long productId, int quantity);

    boolean reserveStock(Long warehouseId, Long productId, int quantity);
}
