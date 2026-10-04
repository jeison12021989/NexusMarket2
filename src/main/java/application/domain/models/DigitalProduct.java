package application.domain.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DIGITAL")
public class DigitalProduct extends Product {

    private String fileFormat;

    protected DigitalProduct() {
        super();
    }

    public DigitalProduct(String name, BigDecimal price, String fileFormat) {
        super(name, price);
        this.fileFormat = fileFormat;
    }

    @Override
    public boolean requiresShipping() {
        return false;
    }

    public String getFileFormat() {
        return fileFormat;
    }
}
