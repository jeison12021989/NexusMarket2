package application.repository;

import application.domain.models.ReturnRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Long> {
}
