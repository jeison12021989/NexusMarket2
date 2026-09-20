package application.repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic persistence abstraction. In Entrega 2 it is backed by an
 * in-memory store; in Entrega 4 these will be backed by Spring Data
 * JPA (MySQL) and/or Spring Data MongoDB, without changing the
 * service layer that depends on this interface.
 */
public interface Repository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();
}
