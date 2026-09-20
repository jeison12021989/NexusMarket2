package application.repository;

import application.domain.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends InMemoryRepository<Product, Long> {
    public ProductRepository() {
        super(Product::getIdentifier);
    }
}
