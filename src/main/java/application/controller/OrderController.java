package application.controller;

import application.domain.models.Invoice;
import application.domain.models.Order;
import application.domain.models.Shipment;
import application.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public Order checkout(@RequestBody Map<String, Object> body) {
        return orderService.checkout(Long.valueOf(body.get("buyerId").toString()));
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
        return orderService.findOrder(id);
    }

    @PostMapping("/{id}/pay")
    public Order pay(@PathVariable Long id) {
        return orderService.payOrder(id);
    }

    @PostMapping("/{id}/dispatch")
    public Shipment dispatch(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return orderService.dispatchOrder(id, body.get("carrier"));
    }

    @PostMapping("/{id}/deliver")
    public Order deliver(@PathVariable Long id) {
        return orderService.deliverOrder(id);
    }

    @PostMapping("/{id}/complete")
    public Order complete(@PathVariable Long id) {
        return orderService.completeOrder(id);
    }

    @PostMapping("/{id}/invoice")
    public Invoice issueInvoice(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return orderService.issueInvoice(id, body.get("invoiceNumber"));
    }
}
