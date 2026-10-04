package application.service;

import application.domain.models.Invoice;
import application.domain.models.Order;
import application.domain.models.Shipment;

public interface OrderService {

    Order checkout(Long buyerId);

    Order payOrder(Long orderId);

    Shipment dispatchOrder(Long orderId, String carrier);

    Order deliverOrder(Long orderId);

    Order completeOrder(Long orderId);

    Invoice issueInvoice(Long orderId, String invoiceNumber);

    Order findOrder(Long orderId);
}
