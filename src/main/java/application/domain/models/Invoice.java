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

/**
 * Domain: Billing. Commercial information associated with a sale (OBJ-09).
 */
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    protected Invoice() {
        // required by JPA
    }

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

    public Long getId() {
        return id;
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
