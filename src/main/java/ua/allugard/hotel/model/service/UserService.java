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
        boolean updated = false;
        try {
            connectionManager.startTransaction();
            updated = daoFactory.createUserDao().update(user);
            updated = daoFactory.createUserAuthenticationDao().update(user.getUserAuthentication());
            connectionManager.commit();
        }catch (Exception e){
            connectionManager.rollback();
        }
        return updated;
    }


    Optional<User> find(int id){
        Optional<User> user;
        user = daoFactory.createUserDao().find(id);
        return user;
    }

    boolean create(User user){
        boolean created = false;
        try {
            connectionManager.startTransaction();
            created = daoFactory.createUserAuthenticationDao().create(user.getUserAuthentication());
            created = daoFactory.createUserDao().create(user);
            connectionManager.commit();
        } catch (Exception e){
            connectionManager.rollback();
        }
        return created;
    }

}
