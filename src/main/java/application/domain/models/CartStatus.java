package application.domain.models;

public enum CartStatus {
    ACTIVE("Active"),
    CONVERTED("Converted"),
    ABANDONED("Abandoned");

    private final String label;

    CartStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
