package application.repository;

import application.domain.models.Seller;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepository extends InMemoryRepository<Seller, Long> {
    public SellerRepository() {
        super(Seller::getIdentifier);
    }
}
