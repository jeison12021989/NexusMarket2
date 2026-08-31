package application.domain.models;

public class MarketplaceWarehouse extends Warehouse {

    public MarketplaceWarehouse(Long identifier, String name, String location) {
        super(identifier, name, location);
    }

    @Override
    public String warehouseType() {
        return "Marketplace Warehouse";
    }
}
