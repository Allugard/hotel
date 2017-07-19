package ua.allugard.hotel.model.dao;

import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 30.06.17.
 */
public interface UserDao extends GenericDao<User> {
    List<User> findUserByFullName(String firstName, String lastName) throws DaoException;
}
