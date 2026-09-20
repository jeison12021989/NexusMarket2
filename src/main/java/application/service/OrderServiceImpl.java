package application.service;

import application.domain.models.Buyer;
import application.domain.models.Cart;
import application.domain.models.CartDetail;
import application.domain.models.Invoice;
import application.domain.models.Order;
import application.domain.models.OrderDetail;
import application.domain.models.OrderStatus;
import application.domain.models.Shipment;
import application.exception.BusinessRuleException;
import application.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BuyerService buyerService;
    private final AtomicLong sequence = new AtomicLong(1001);

    public OrderServiceImpl(OrderRepository orderRepository, BuyerService buyerService) {
        this.orderRepository = orderRepository;
        this.buyerService = buyerService;
    }

    @Override
    public Order checkout(Long buyerId) {
        Buyer buyer = buyerService.findBuyer(buyerId);
        Cart cart = buyer.getCart();
        if (cart == null || cart.getDetails().isEmpty()) {
            throw new BusinessRuleException("Cart is empty, there is nothing to check out.");
        }

        Order order = new Order(sequence.getAndIncrement(), buyer);
        for (CartDetail detail : cart.getDetails()) {
            order.addDetail(new OrderDetail(detail.getProduct(), detail.getQuantity()));
        }
        order.confirmOrder();
        buyer.placeOrder(order);
        cart.convertToOrder();

        return orderRepository.save(order);
    }

    @Override
    public Order payOrder(Long orderId) {
        Order order = findOrder(orderId);
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new BusinessRuleException("Order " + orderId + " is not pending payment.");
        }
        order.pay();
        return order;
    }

    @Override
    public Shipment dispatchOrder(Long orderId, String carrier) {
        Order order = findOrder(orderId);
        if (order.getStatus() != OrderStatus.PAID) {
            throw new BusinessRuleException("Order " + orderId + " must be paid before it can be dispatched.");
        }
        order.ship();
        Shipment shipment = new Shipment(order, carrier);
        shipment.ship();
        return shipment;
    }

    @Override
    public Order deliverOrder(Long orderId) {
        Order order = findOrder(orderId);
        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new BusinessRuleException("Order " + orderId + " has not been shipped yet.");
        }
        order.deliver();
        return order;
    }

    @Override
    public Order completeOrder(Long orderId) {
        Order order = findOrder(orderId);
        if (order.getStatus() != OrderStatus.DELIVERED) {
            throw new BusinessRuleException("Order " + orderId + " has not been delivered yet.");
        }
        order.complete();
        return order;
    }

    @Override
    public Invoice issueInvoice(Long orderId, String invoiceNumber) {
        Order order = findOrder(orderId);
        if (order.getStatus() == OrderStatus.CART || order.getStatus() == OrderStatus.PENDING_PAYMENT) {
            throw new BusinessRuleException("Cannot invoice an order that has not been paid yet.");
        }
        return new Invoice(invoiceNumber, order);
    }

    @Override
    public Order findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessRuleException("Order " + orderId + " does not exist."));
    }
}
