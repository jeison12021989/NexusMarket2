package application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "warehouses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "warehouse_type")
public abstract class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifier;

    private String name;

    private String location;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private Seller seller;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> inventories = new ArrayList<>();

    protected Warehouse() {
    }

    protected Warehouse(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public abstract String warehouseType();

    public void addInventory(Inventory inventory) {
        inventory.setWarehouse(this);
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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }
}