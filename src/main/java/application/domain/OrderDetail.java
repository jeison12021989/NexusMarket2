package application.domain.models;

import java.math.BigDecimal;

public class OrderDetail {

    private final Product product;
    private final int quantity;

    public OrderDetail(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal subtotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
