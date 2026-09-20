package application.repository;

import application.domain.models.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends InMemoryRepository<Order, Long> {
    public OrderRepository() {
        super(Order::getIdentifier);
    }
}
