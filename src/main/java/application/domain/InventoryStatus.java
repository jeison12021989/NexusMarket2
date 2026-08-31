package application.domain.models;

public enum InventoryStatus {
    AVAILABLE("Available"),
    DAMAGED("Damaged");

    private final String label;

    InventoryStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
