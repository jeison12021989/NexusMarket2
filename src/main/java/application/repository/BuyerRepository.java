package application.repository;

import application.domain.models.Buyer;
import org.springframework.stereotype.Repository;

@Repository
public class BuyerRepository extends InMemoryRepository<Buyer, Long> {
    public BuyerRepository() {
        super(Buyer::getIdentifier);
    }
}
