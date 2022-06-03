package eg.gov.iti.jets.persistence.dao;

import java.util.List;
import java.util.Optional;

interface GenericCrudDao<T, Id> {
    T save(T entity);

    T update(T entity);

    Optional<T> findById(Id id);

    List<T> findAll();

    List<T> findAll(int pageNumber,int pageSize);

    List<T> findAllByExample(T example);


}
