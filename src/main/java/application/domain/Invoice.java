package application.domain.models;

import java.math.BigDecimal;

public class Invoice {

    private final String number;
    private final Order order;
    private final BigDecimal total;
    private InvoiceStatus status;

    public Invoice(String number, Order order) {
        this.number = number;
        this.order = order;
        this.total = order.calculateTotal();
        this.status = InvoiceStatus.ISSUED;
    }

    public void showInvoice() {
        System.out.println("Invoice: " + number);
        System.out.println("Total: $" + total);
        System.out.println("Status: " + status.getLabel());
    }

    public String getNumber() {
        return number;
    }

    public Order getOrder() {
        return order;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public InvoiceStatus getStatus() {
        return status;
    }
}
