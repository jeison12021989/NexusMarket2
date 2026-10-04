package application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain 7 - Order Management. First state of the order life cycle:
 * "Carrito: Seleccion provisional de productos".
 */
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "buyer_id")
    @JsonIgnore
    private Buyer buyer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartDetail> details = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    protected Cart() {
        // required by JPA
    }

    public Cart(Buyer buyer) {
        this.buyer = buyer;
        this.status = CartStatus.ACTIVE;
    }

    public void addProduct(CartDetail detail) {
        if (status == CartStatus.ACTIVE) {
            detail.setCart(this);
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

    public Long getId() {
        return id;
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
