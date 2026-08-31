package application.domain.models;

public enum InvoiceStatus {
    ISSUED("Issued"),
    CANCELLED("Cancelled");

    private final String label;

    InvoiceStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
