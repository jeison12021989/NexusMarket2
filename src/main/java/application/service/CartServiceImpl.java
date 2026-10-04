package application.service;

import application.domain.models.Buyer;
import application.domain.models.Cart;
import application.domain.models.CartDetail;
import application.domain.models.Product;
import application.exception.BusinessRuleException;
import application.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final BuyerService buyerService;
    private final ProductRepository productRepository;

    public CartServiceImpl(BuyerService buyerService, ProductRepository productRepository) {
        this.buyerService = buyerService;
        this.productRepository = productRepository;
    }

    @Override
    public Cart getOrCreateCart(Long buyerId) {
        Buyer buyer = buyerService.findBuyer(buyerId);
        if (buyer.getCart() == null) {
            buyer.setCart(new Cart(buyer));
        }
        return buyer.getCart();
    }

    @Override
    public void addProduct(Long buyerId, Long productId, int quantity) {
        Cart cart = getOrCreateCart(buyerId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessRuleException("Product " + productId + " does not exist."));
        cart.addProduct(new CartDetail(product, quantity));
    }

    @Override
    public BigDecimal calculateTotal(Long buyerId) {
        return getOrCreateCart(buyerId).calculateTotal();
    }
}
