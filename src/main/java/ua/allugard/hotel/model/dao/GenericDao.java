package ua.allugard.hotel.model.dao;

import ua.allugard.hotel.util.exceptions.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 30.06.17.
 */
public interface GenericDao<T> {
    Optional<T> find(int id) throws DaoException;
    List<T> findAll() throws DaoException;
    boolean create(T t) throws DaoException;
    boolean update(T t) throws DaoException;
    boolean delete(int id) throws DaoException;

    default int generateId(PreparedStatement statement) throws SQLException {
        int id = -1;
        ResultSet rs = statement.getGeneratedKeys();
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }
}
