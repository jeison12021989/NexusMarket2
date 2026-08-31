package application.domain.models;

public enum ProductStatus {
    PUBLISHED("Published"),
    SUSPENDED("Suspended"),
    DISCONTINUED("Discontinued");

    private final String label;

    ProductStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
