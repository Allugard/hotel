package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.dao.util.DatabaseConnection;
import ua.allugard.hotel.model.entity.User;

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



    boolean update(User user){
        boolean updated;
        try (DatabaseConnection connection = connectionManager.getConnection()) {
            connection.startTransaction();
            updated = daoFactory.createUserDao(connection).update(user);
            updated = daoFactory.createUserAuthenticationDao(connection).update(user.getUserAuthentication());
            connection.commit();
        }
        return updated;
    }


    Optional<User> find(int id){
        Optional<User> user;
        try (DatabaseConnection connection = connectionManager.getConnection()) {
            user = daoFactory.createUserDao(connection).find(id);
        }
        return user;
    }

    boolean create(User user){
        boolean created;
        try(DatabaseConnection connection = connectionManager.getConnection()) {
            connection.startTransaction();
            created = daoFactory.createUserDao(connection).create(user);
            connection.commit();
        }
        return created;
    }

}
