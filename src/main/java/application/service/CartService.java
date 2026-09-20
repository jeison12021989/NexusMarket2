package application.service;

import application.domain.models.Cart;

import java.math.BigDecimal;

public interface CartService {

    Cart getOrCreateCart(Long buyerId);

    void addProduct(Long buyerId, Long productId, int quantity);

    BigDecimal calculateTotal(Long buyerId);
}
