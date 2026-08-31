package application.domain.models;

public class InventoryMovement {

    private final MovementType type;
    private final int quantity;

    public InventoryMovement(MovementType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public void showMovement() {
        System.out.println("Movement: " + type.getLabel() + " - Quantity: " + quantity);
    }

    public MovementType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
}
