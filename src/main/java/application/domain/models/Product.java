package application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain 5 - Catalog Management.
 * The catalog distinguishes physical products (require inventory and
 * shipping) from digital products (immediate delivery after payment).
 */
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifier;

    private String name;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private Seller seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> variants = new ArrayList<>();

    protected Product() {
        // required by JPA
    }

    protected Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.status = ProductStatus.PUBLISHED;
    }

    public abstract boolean requiresShipping();

    public void showProduct() {
        System.out.println("Product: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Status: " + status.getLabel());
    }

    public void addVariant(ProductVariant variant) {
        variant.setProduct(this);
        variants.add(variant);
    }

    public Long getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }
}
