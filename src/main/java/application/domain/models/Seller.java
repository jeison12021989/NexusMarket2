package application.domain.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain 3 - Seller Management.
 * Sellers cannot self-register; they are onboarded by an Administrator
 * (Business Rule, Domain 3).
 */
@Entity
@DiscriminatorValue("SELLER")
public class Seller extends User {

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Warehouse> warehouses = new ArrayList<>();

    protected Seller() {
        super();
    }

    public Seller(String name, String email, String document) {
        super(name, email, document);
    }

    @Override
    public String showRole() {
        return "Seller";
    }

    public void registerProduct(Product product) {
        product.setSeller(this);
        products.add(product);
    }

    public void addWarehouse(SellerWarehouse warehouse) {
        warehouse.setSeller(this);
        warehouses.add(warehouse);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }
}
