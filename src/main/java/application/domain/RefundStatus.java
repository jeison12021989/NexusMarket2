package application.domain.models;

public enum RefundStatus {
    PENDING("Pending"),
    PROCESSED("Processed");

    private final String label;

    RefundStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
