package application.domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory is distributed and must always be linked to one product and
 * one warehouse. Negative stock is never allowed.
 */
public class Inventory {

    private final Product product;
    private final Warehouse warehouse;
    private int quantity;
    private InventoryStatus status;
    private final List<InventoryMovement> movements = new ArrayList<>();

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
        movements.add(new InventoryMovement(movementType, movementQuantity));
    }

    public Product getProduct() {
        return product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
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
