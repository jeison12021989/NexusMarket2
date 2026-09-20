package application.service;

import application.domain.models.Inventory;
import application.domain.models.Product;
import application.domain.models.Seller;
import application.domain.models.SellerWarehouse;
import application.domain.models.Warehouse;
import application.exception.BusinessRuleException;
import application.repository.ProductRepository;
import application.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private final SellerService sellerService;
    private final AtomicLong sequence = new AtomicLong(201);

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                 ProductRepository productRepository,
                                 SellerService sellerService) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
        this.sellerService = sellerService;
    }

    @Override
    public SellerWarehouse createSellerWarehouse(Long sellerId, String name, String location) {
        Seller seller = sellerService.findSeller(sellerId);
        SellerWarehouse warehouse = new SellerWarehouse(sequence.getAndIncrement(), name, location);
        seller.addWarehouse(warehouse);
        warehouseRepository.save(warehouse);
        return warehouse;
    }

    @Override
    public void registerInventory(Long warehouseId, Long productId, int quantity) {
        Warehouse warehouse = findWarehouse(warehouseId);
        Product product = findProduct(productId);
        warehouse.addInventory(new Inventory(product, warehouse, quantity));
    }

    @Override
    public boolean reserveStock(Long warehouseId, Long productId, int quantity) {
        Warehouse warehouse = findWarehouse(warehouseId);
        return warehouse.getInventories().stream()
                .filter(inventory -> inventory.getProduct().getIdentifier().equals(productId))
                .findFirst()
                .map(inventory -> inventory.reserve(quantity))
                .orElseThrow(() -> new BusinessRuleException(
                        "There is no inventory for product " + productId + " in warehouse " + warehouseId + "."));
    }

    private Warehouse findWarehouse(Long warehouseId) {
        return warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new BusinessRuleException("Warehouse " + warehouseId + " does not exist."));
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BusinessRuleException("Product " + productId + " does not exist."));
    }
}