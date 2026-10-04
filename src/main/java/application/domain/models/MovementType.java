package application.domain.models;

public enum MovementType {
    INCOME("Income"),
    RESERVATION("Reservation"),
    SALE_OUTPUT("Sale Output"),
    ADJUSTMENT("Adjustment"),
    RETURN("Return");

    private final String label;

    MovementType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
