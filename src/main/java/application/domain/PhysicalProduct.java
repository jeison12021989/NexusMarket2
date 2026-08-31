package application.domain.models;

import java.math.BigDecimal;

public class PhysicalProduct extends Product {

    private final double weight;
    private final ProductType type = ProductType.PHYSICAL;

    public PhysicalProduct(Long identifier, String name, BigDecimal price, double weight) {
        super(identifier, name, price);
        this.weight = weight;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    public double getWeight() {
        return weight;
    }

    public ProductType getType() {
        return type;
    }
}
