package ua.allugard.hotel.model.dao;

import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 30.06.17.
 */
public interface GenericDao<T> {
    Optional<T> find(int id);
    List<T> findAll();
    boolean create(T t);
    boolean update(T t);
    boolean delete(int id);
}
