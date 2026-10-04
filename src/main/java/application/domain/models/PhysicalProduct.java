package application.domain.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("PHYSICAL")
public class PhysicalProduct extends Product {

    private double weight;

    protected PhysicalProduct() {
        super();
    }

    public PhysicalProduct(String name, BigDecimal price, double weight) {
        super(name, price);
        this.weight = weight;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    public double getWeight() {
        return weight;
    }
}
