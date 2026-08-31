package application.domain.models;

public enum OrderStatus {
    CART("Cart"),
    PENDING_PAYMENT("Pending Payment"),
    PAID("Paid"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    COMPLETED("Completed");

    private final String label;

    OrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
