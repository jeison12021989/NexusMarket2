package application.domain.models;

public class Shipment {

    private final Order order;
    private final String carrier;
    private ShipmentStatus status;

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
