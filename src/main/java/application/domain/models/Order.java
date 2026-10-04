package application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain 7 - Order Management. Represents the formal commercial
 * commitment; its life cycle is the central process of the system.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifier;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @JsonIgnore
    private Buyer buyer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> details = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Order() {
        // required by JPA
    }

    public Order(Buyer buyer) {
        this.buyer = buyer;
        this.status = OrderStatus.CART;
    }

    public void addDetail(OrderDetail detail) {
        if (status != OrderStatus.COMPLETED) {
            detail.setOrder(this);
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
