package application.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Domain: After-sales. Return and refund handling (OBJ-11).
 */
@Entity
public class ReturnRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String reason;

    @Enumerated(EnumType.STRING)
    private ReturnStatus status;

    protected ReturnRequest() {
        // required by JPA
    }

    public ReturnRequest(Order order, String reason) {
        this.order = order;
        this.reason = reason;
        this.status = ReturnStatus.REQUESTED;
    }

    public void approve() {
        this.status = ReturnStatus.APPROVED;
        System.out.println("Return approved.");
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public String getReason() {
        return reason;
    }

    public ReturnStatus getStatus() {
        return status;
    }
}
