package application.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "return_request_id")
    private ReturnRequest returnRequest;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private RefundStatus status;

    protected Refund() {
        // required by JPA
    }

    public Refund(ReturnRequest returnRequest, BigDecimal amount) {
        this.returnRequest = returnRequest;
        this.amount = amount;
        this.status = RefundStatus.PENDING;
    }

    public void process() {
        if (returnRequest.getStatus() == ReturnStatus.APPROVED) {
            this.status = RefundStatus.PROCESSED;
            System.out.println("Refund processed: $" + amount);
        }
    }

    public Long getId() {
        return id;
    }

    public ReturnRequest getReturnRequest() {
        return returnRequest;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public RefundStatus getStatus() {
        return status;
    }
}
