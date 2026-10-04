package application.domain.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SELLER_WAREHOUSE")
public class SellerWarehouse extends Warehouse {

    protected SellerWarehouse() {
        super();
    }

    public SellerWarehouse(String name, String location) {
        super(name, location);
    }

    @Override
    public String warehouseType() {
        return "Seller Warehouse";
    }
}