package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.dao.util.DatabaseConnection;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.entity.UserAuthentication;

import java.util.Optional;

/**
 * Created by allugard on 05.07.17.
 */
public class UserService {

    private ConnectionManager connectionManager;
    private DaoFactory daoFactory;

    UserService(ConnectionManager connectionManager, DaoFactory daoFactory) {
        this.connectionManager = connectionManager;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final UserService INSTANCE = new UserService(ConnectionManager.getInstance(), DaoFactory.getInstance());
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }



    public boolean update(User user){
        boolean updated;
        try (DatabaseConnection connection = connectionManager.getConnection()) {
            connection.startTransaction();
            updated = daoFactory.createUserDao(connection).update(user);
            updated = daoFactory.createUserAuthenticationDao(connection).update(user.getUserAuthentication());
            connection.commit();
        }
        return updated;
    }



    public Optional<User> findUserByLoginPassword(String login, String password){
        Optional<User> user = Optional.empty();
        Optional<UserAuthentication> userAuthentication;
        try (DatabaseConnection connection = connectionManager.getConnection()) {
            userAuthentication = daoFactory.createUserAuthenticationDao(connection).findUserByLogin(login);
            if (userAuthentication.isPresent() && correctPassword(userAuthentication.get(), password)) {
                user = daoFactory.createUserDao(connection).find(userAuthentication.get().getId());
            }
        }
        return user;
    }

    private boolean correctPassword(UserAuthentication userAuthentication, String password) {
        boolean res = false;
        if (password.equals(userAuthentication.getPassword())){
            res = true;
        }
        return res;
    }

    public boolean create(User user){
        boolean created;
        try(DatabaseConnection connection = connectionManager.getConnection()) {
            connection.startTransaction();
            created = daoFactory.createUserAuthenticationDao(connection).create(user.getUserAuthentication());
            created = daoFactory.createUserDao(connection).create(user);
            connection.commit();
        }
        return created;
    }

}
