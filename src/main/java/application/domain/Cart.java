package application.domain.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final Buyer buyer;
    private final List<CartDetail> details = new ArrayList<>();
    private CartStatus status;

    public Cart(Buyer buyer) {
        this.buyer = buyer;
        this.status = CartStatus.ACTIVE;
    }

    public void addProduct(CartDetail detail) {
        if (status == CartStatus.ACTIVE) {
            details.add(detail);
        }
    }

    public BigDecimal calculateTotal() {
        return details.stream()
                .map(CartDetail::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void convertToOrder() {
        this.status = CartStatus.CONVERTED;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public List<CartDetail> getDetails() {
        return details;
    }

    public CartStatus getStatus() {
        return status;
    }
}
