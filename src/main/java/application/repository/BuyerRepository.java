package application.repository;

import application.domain.models.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
