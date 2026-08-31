package application.domain.models;

public class SellerWarehouse extends Warehouse {

    public SellerWarehouse(Long identifier, String name, String location) {
        super(identifier, name, location);
    }

    @Override
    public String warehouseType() {
        return "Seller Warehouse";
    }
}
