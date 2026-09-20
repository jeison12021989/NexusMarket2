package application.repository;

import application.domain.models.Warehouse;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseRepository extends InMemoryRepository<Warehouse, Long> {
    public WarehouseRepository() {
        super(Warehouse::getIdentifier);
    }
}
