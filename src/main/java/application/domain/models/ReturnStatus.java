package application.domain.models;

public enum ReturnStatus {
    REQUESTED("Requested"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String label;

    ReturnStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
