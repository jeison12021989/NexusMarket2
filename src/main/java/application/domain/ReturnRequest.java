package application.domain.models;

public class ReturnRequest {

    private final Order order;
    private final String reason;
    private ReturnStatus status;

    public ReturnRequest(Order order, String reason) {
        this.order = order;
        this.reason = reason;
        this.status = ReturnStatus.REQUESTED;
    }

    public void approve() {
        this.status = ReturnStatus.APPROVED;
        System.out.println("Return approved.");
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
