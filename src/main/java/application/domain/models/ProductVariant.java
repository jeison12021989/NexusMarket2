package application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents differences of color, size, model, etc. within a product.
 */
@Entity
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    private String sku;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    protected ProductVariant() {
        // required by JPA
    }

    public ProductVariant(String name, String value, String sku) {
        this.name = name;
        this.value = value;
        this.sku = sku;
    }

    public void showVariant() {
        System.out.println(name + ": " + value + " - SKU: " + sku);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getSku() {
        return sku;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
