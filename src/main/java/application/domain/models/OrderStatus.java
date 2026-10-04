package application.domain.models;

/**
 * Order life cycle (Domain 7): Cart -> Pending Payment -> Paid -> Shipped
 * -> Delivered -> Completed. A completed order can never be modified
 * (Critical Validation - Orders).
 */
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
