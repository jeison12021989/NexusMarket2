package application.domain.models;

import java.math.BigDecimal;

public class Refund {

    private final ReturnRequest returnRequest;
    private final BigDecimal amount;
    private RefundStatus status;

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
