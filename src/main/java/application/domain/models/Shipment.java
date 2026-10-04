package application.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Domain: Shipping. Logistics process for physical products (OBJ-10).
 */
@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String carrier;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    protected Shipment() {
        // required by JPA
    }

    public Shipment(Order order, String carrier) {
        this.order = order;
        this.carrier = carrier;
        this.status = ShipmentStatus.PENDING;
    }

    public void ship() {
        this.status = ShipmentStatus.IN_TRANSIT;
        System.out.println("Shipment dispatched.");
    }

    public void deliver() {
        this.status = ShipmentStatus.DELIVERED;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public String getCarrier() {
        return carrier;
    }

    public ShipmentStatus getStatus() {
        return status;
    }
}
