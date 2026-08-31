package application.domain.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The catalog distinguishes physical products (require inventory and
 * shipping) from digital products (immediate delivery after payment).
 */
public abstract class Product {

    private final Long identifier;
    private String name;
    private BigDecimal price;
    private ProductStatus status;
    private final List<ProductVariant> variants = new ArrayList<>();

    protected Product(Long identifier, String name, BigDecimal price) {
        this.identifier = identifier;
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

    public List<ProductVariant> getVariants() {
        return variants;
    }
}
