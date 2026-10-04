package application.domain.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Handles the physical operation of warehouses and dispatches
 * (Participant: Operador Logistico).
 */
@Entity
@DiscriminatorValue("LOGISTICS_OPERATOR")
public class LogisticsOperator extends User {

    protected LogisticsOperator() {
        super();
    }

    public LogisticsOperator(String name, String email, String document) {
        super(name, email, document);
    }

    @Override
    public String showRole() {
        return "Logistics Operator";
    }

    public void prepareOrder(Order order) {
        if (order.getStatus() == OrderStatus.PAID) {
            System.out.println("Preparing order " + order.getIdentifier() + ".");
        } else {
            System.out.println("The order must be paid before it can be prepared.");
        }
    }
}
