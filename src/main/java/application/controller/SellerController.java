package application.controller;

import application.domain.models.Seller;
import application.service.SellerService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping
    public Seller register(@RequestBody Map<String, String> body) {
        return sellerService.registerSeller(body.get("name"), body.get("email"), body.get("document"));
    }

    @GetMapping("/{id}")
    public Seller findById(@PathVariable Long id) {
        return sellerService.findSeller(id);
    }
}
