package ua.allugard.hotel.model.dao;

import ua.allugard.hotel.model.entity.UserAuthentication;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.util.Optional;

/**
 * Created by allugard on 30.06.17.
 */
public interface UserAuthenticationDao extends GenericDao<UserAuthentication> {
    Optional<UserAuthentication> findUserByLogin(String login) throws DaoException;

}
