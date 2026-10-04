package application.controller;

import application.domain.models.Buyer;
import application.service.BuyerService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping
    public Buyer register(@RequestBody Map<String, String> body) {
        return buyerService.registerBuyer(body.get("name"), body.get("email"), body.get("document"));
    }

    @GetMapping("/{id}")
    public Buyer findById(@PathVariable Long id) {
        return buyerService.findBuyer(id);
    }
}
