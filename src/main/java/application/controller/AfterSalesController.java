package application.controller;

import application.domain.models.Refund;
import application.domain.models.ReturnRequest;
import application.service.AfterSalesService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/returns")
public class AfterSalesController {

    private final AfterSalesService afterSalesService;

    public AfterSalesController(AfterSalesService afterSalesService) {
        this.afterSalesService = afterSalesService;
    }

    @PostMapping
    public ReturnRequest requestReturn(@RequestBody Map<String, Object> body) {
        return afterSalesService.requestReturn(
                Long.valueOf(body.get("orderId").toString()),
                body.get("reason").toString());
    }

    @PostMapping("/{id}/approve")
    public ReturnRequest approve(@PathVariable Long id) {
        return afterSalesService.approveReturn(id);
    }

    @PostMapping("/{id}/refund")
    public Refund refund(@PathVariable Long id) {
        return afterSalesService.processRefund(id);
    }
}
