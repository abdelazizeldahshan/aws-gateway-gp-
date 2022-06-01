package eg.gov.iti.jets.persistence.dao;

import java.util.Optional;

interface GenericDaoCRUD<T, Id> {
    T save(T entity);

    T update(T entity);

    Optional<T> findById(Id id);
}