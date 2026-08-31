package application.domain.models;

import java.math.BigDecimal;

public class DigitalProduct extends Product {

    private final String fileFormat;
    private final ProductType type = ProductType.DIGITAL;

    public DigitalProduct(Long identifier, String name, BigDecimal price, String fileFormat) {
        super(identifier, name, price);
        this.fileFormat = fileFormat;
    }

    @Override
    public boolean requiresShipping() {
        return false;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public ProductType getType() {
        return type;
    }
}
