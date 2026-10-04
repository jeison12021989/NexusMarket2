package application.domain.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MARKETPLACE")
public class MarketplaceWarehouse extends Warehouse {

    protected MarketplaceWarehouse() {
        super();
    }

    public MarketplaceWarehouse(String name, String location) {
        super(name, location);
    }

    @Override
    public String warehouseType() {
        return "Marketplace Warehouse";
    }
}
