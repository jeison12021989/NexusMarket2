package application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MovementType type;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @JsonIgnore
    private Inventory inventory;

    protected InventoryMovement() {
        // required by JPA
    }

    public InventoryMovement(MovementType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public void showMovement() {
        System.out.println("Movement: " + type.getLabel() + " - Quantity: " + quantity);
    }

    public Long getId() {
        return id;
    }

    public MovementType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
