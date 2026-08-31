package application.domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Sellers cannot self-register; they are onboarded by an Administrator.
 */
public class Seller extends User {

    private final List<Product> products = new ArrayList<>();
    private final List<Warehouse> warehouses = new ArrayList<>();

    public Seller(Long identifier, String name, String email, String document) {
        super(identifier, name, email, document);
    }

    @Override
    public String showRole() {
        return "Seller";
    }

    public void registerProduct(Product product) {
        products.add(product);
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }
}
