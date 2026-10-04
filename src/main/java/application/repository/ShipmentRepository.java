package application.repository;

import application.domain.models.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
