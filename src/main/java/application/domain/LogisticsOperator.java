package application.domain.models;

/**
 * Handles the physical operation of warehouses and dispatches.
 */
public class LogisticsOperator extends User {

    public LogisticsOperator(Long identifier, String name, String email, String document) {
        super(identifier, name, email, document);
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
