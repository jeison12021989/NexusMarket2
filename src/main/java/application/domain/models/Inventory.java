package application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain 6 - Inventory Management.
 * Inventory is distributed and must always be linked to one product and
 * one warehouse. Negative stock is never allowed (Domain 6 restriction).
 */
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnore
    private Warehouse warehouse;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private InventoryStatus status;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryMovement> movements = new ArrayList<>();

    protected Inventory() {
        // required by JPA
    }

    public Inventory(Product product, Warehouse warehouse, int quantity) {
        this(product, warehouse, quantity, InventoryStatus.AVAILABLE);
    }

    public Inventory(Product product, Warehouse warehouse, int quantity, InventoryStatus status) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Inventory quantity cannot be negative.");
        }
        this.product = product;
        this.warehouse = warehouse;
        this.quantity = quantity;
        this.status = status;
    }

    public boolean reserve(int quantityToReserve) {
        if (quantityToReserve <= 0) {
            System.out.println("Quantity must be positive.");
            return false;
        }
        if (status == InventoryStatus.DAMAGED) {
            System.out.println("Damaged inventory cannot be reserved.");
            return false;
        }
        if (quantityToReserve > quantity) {
            System.out.println("There is not enough inventory.");
            return false;
        }
        quantity -= quantityToReserve;
        registerMovement(MovementType.RESERVATION, quantityToReserve);
        System.out.println(quantityToReserve + " units reserved.");
        return true;
    }

    public void registerMovement(MovementType movementType, int movementQuantity) {
        if (movementQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        InventoryMovement movement = new InventoryMovement(movementType, movementQuantity);
        movement.setInventory(this);
        movements.add(movement);
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public int getQuantity() {
        return quantity;
    }

    public InventoryStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryStatus status) {
        this.status = status;
    }

    public List<InventoryMovement> getMovements() {
        return movements;
    }
}
