package application.domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Distinguishes Marketplace warehouses from Seller warehouses.
 */
public abstract class Warehouse {

    private final Long identifier;
    private String name;
    private String location;
    private final List<Inventory> inventories = new ArrayList<>();

    protected Warehouse(Long identifier, String name, String location) {
        this.identifier = identifier;
        this.name = name;
        this.location = location;
    }

    public abstract String warehouseType();

    public void addInventory(Inventory inventory) {
        inventories.add(inventory);
    }

    public Long getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }
}
