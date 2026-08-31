package application.domain.models;

public enum ProductType {
    PHYSICAL("Physical"),
    DIGITAL("Digital");

    private final String label;

    ProductType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
