package application.domain.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the formal commercial commitment; its life cycle is the
 * central process of the system.
 */
public class Order {

    private final Long identifier;
    private final Buyer buyer;
    private final List<OrderDetail> details = new ArrayList<>();
    private OrderStatus status;

    public Order(Long identifier, Buyer buyer) {
        this.identifier = identifier;
        this.buyer = buyer;
        this.status = OrderStatus.CART;
    }

    public void addDetail(OrderDetail detail) {
        if (status != OrderStatus.COMPLETED) {
            details.add(detail);
        }
    }

    public BigDecimal calculateTotal() {
        return details.stream()
                .map(OrderDetail::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void confirmOrder() {
        if (status == OrderStatus.CART && !details.isEmpty()) {
            status = OrderStatus.PENDING_PAYMENT;
        }
    }

    public void pay() {
        if (status == OrderStatus.PENDING_PAYMENT) {
            status = OrderStatus.PAID;
        }
    }

    public void ship() {
        if (status == OrderStatus.PAID) {
            status = OrderStatus.SHIPPED;
        }
    }

    public void deliver() {
        if (status == OrderStatus.SHIPPED) {
            status = OrderStatus.DELIVERED;
        }
    }

    public void complete() {
        if (status == OrderStatus.DELIVERED) {
            status = OrderStatus.COMPLETED;
        }
    }

    public Long getIdentifier() {
        return identifier;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
