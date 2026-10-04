package application.service;

import application.domain.models.DigitalProduct;
import application.domain.models.PhysicalProduct;
import application.domain.models.ProductVariant;

import java.math.BigDecimal;

public interface CatalogService {

    PhysicalProduct registerPhysicalProduct(Long sellerId, String name, BigDecimal price, double weight);

    DigitalProduct registerDigitalProduct(Long sellerId, String name, BigDecimal price, String fileFormat);

    void addVariant(Long productId, ProductVariant variant);
}
