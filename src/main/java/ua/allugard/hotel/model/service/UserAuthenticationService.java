package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.dao.util.DatabaseConnection;
import ua.allugard.hotel.model.entity.UserAuthentication;

import java.util.Optional;

/**
 * Created by allugard on 05.07.17.
 */
public class UserAuthenticationService {

    private ConnectionManager connectionManager;
    private DaoFactory daoFactory;

    UserAuthenticationService(ConnectionManager connectionManager, DaoFactory daoFactory) {
        this.connectionManager = connectionManager;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final UserAuthenticationService INSTANCE = new UserAuthenticationService(ConnectionManager.getInstance(), DaoFactory.getInstance());
    }

    public static UserAuthenticationService getInstance() {
        return Holder.INSTANCE;
    }

    Optional<UserAuthentication> findUserByLogin(String login){
        Optional<UserAuthentication> userAuthentication;
        try (DatabaseConnection connection = connectionManager.getConnection()) {
            userAuthentication = daoFactory.createUserAuthenticationDao(connection).findUserByLogin(login);
        }
        return userAuthentication;
    }

    boolean update(UserAuthentication userAuthentication){
        boolean updated;
        try (DatabaseConnection connection = connectionManager.getConnection()) {
            updated = daoFactory.createUserAuthenticationDao(connection).update(userAuthentication);
        }
        return updated;
    }

}
