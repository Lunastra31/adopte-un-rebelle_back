package ldnr.groupe3.adopteunrebelle.services;

import java.util.List;

public interface AbstractService<T> {
    Integer save(T entity);

    List<T> findAll();

    T findById(Integer id);

    void delete(Integer id);
}
