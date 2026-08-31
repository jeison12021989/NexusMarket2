package application.domain.models;

public enum ShipmentStatus {
    PENDING("Pending"),
    IN_TRANSIT("In Transit"),
    DELIVERED("Delivered");

    private final String label;

    ShipmentStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
