package application.controller;

import application.domain.models.Cart;
import application.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/buyers/{buyerId}/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Cart getCart(@PathVariable Long buyerId) {
        return cartService.getOrCreateCart(buyerId);
    }

    @PostMapping("/items")
    public void addProduct(@PathVariable Long buyerId, @RequestBody Map<String, Object> body) {
        cartService.addProduct(
                buyerId,
                Long.valueOf(body.get("productId").toString()),
                Integer.parseInt(body.get("quantity").toString()));
    }

    @GetMapping("/total")
    public BigDecimal total(@PathVariable Long buyerId) {
        return cartService.calculateTotal(buyerId);
    }
}
