package application.service;

import application.domain.models.DigitalProduct;
import application.domain.models.PhysicalProduct;
import application.domain.models.Product;
import application.domain.models.ProductVariant;
import application.domain.models.Seller;
import application.exception.BusinessRuleException;
import application.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

    private final ProductRepository productRepository;
    private final SellerService sellerService;

    public CatalogServiceImpl(ProductRepository productRepository, SellerService sellerService) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
    }

    @Override
    public PhysicalProduct registerPhysicalProduct(Long sellerId, String name, BigDecimal price, double weight) {
        Seller seller = sellerService.findSeller(sellerId);
        PhysicalProduct product = new PhysicalProduct(name, price, weight);
        seller.registerProduct(product);
        return productRepository.save(product);
    }

    @Override
    public DigitalProduct registerDigitalProduct(Long sellerId, String name, BigDecimal price, String fileFormat) {
        Seller seller = sellerService.findSeller(sellerId);
        DigitalProduct product = new DigitalProduct(name, price, fileFormat);
        seller.registerProduct(product);
        return productRepository.save(product);
    }

    @Override
    public void addVariant(Long productId, ProductVariant variant) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessRuleException("Product " + productId + " does not exist."));
        product.addVariant(variant);
    }
}
