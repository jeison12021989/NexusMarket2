package application.domain.models;

public class ProductVariant {

    private final String name;
    private final String value;
    private final String sku;

    public ProductVariant(String name, String value, String sku) {
        this.name = name;
        this.value = value;
        this.sku = sku;
    }

    public void showVariant() {
        System.out.println(name + ": " + value + " - SKU: " + sku);
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
}
