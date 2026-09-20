package application.service;

import application.domain.models.DigitalProduct;
import application.domain.models.PhysicalProduct;
import application.domain.models.Product;
import application.domain.models.ProductVariant;
import application.domain.models.Seller;
import application.exception.BusinessRuleException;
import application.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final ProductRepository productRepository;
    private final SellerService sellerService;
    private final AtomicLong sequence = new AtomicLong(101);

    public CatalogServiceImpl(ProductRepository productRepository, SellerService sellerService) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
    }

    @Override
    public PhysicalProduct registerPhysicalProduct(Long sellerId, String name, BigDecimal price, double weight) {
        Seller seller = sellerService.findSeller(sellerId);
        PhysicalProduct product = new PhysicalProduct(sequence.getAndIncrement(), name, price, weight);
        seller.registerProduct(product);
        productRepository.save(product);
        return product;
    }

    @Override
    public DigitalProduct registerDigitalProduct(Long sellerId, String name, BigDecimal price, String fileFormat) {
        Seller seller = sellerService.findSeller(sellerId);
        DigitalProduct product = new DigitalProduct(sequence.getAndIncrement(), name, price, fileFormat);
        seller.registerProduct(product);
        productRepository.save(product);
        return product;
    }

    @Override
    public void addVariant(Long productId, ProductVariant variant) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessRuleException("Product " + productId + " does not exist."));
        product.addVariant(variant);
    }
}