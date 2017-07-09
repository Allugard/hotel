package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
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


    public Optional<User> findUserByLoginPassword(String login, String password){
        Optional<User> user = Optional.empty();
        Optional<UserAuthentication> userAuthentication;
        try {
            userAuthentication = daoFactory.createUserAuthenticationDao().findUserByLogin(login);
            if (userAuthentication.isPresent() && correctPassword(userAuthentication.get(), password)) {
                user = daoFactory.createUserDao().find(userAuthentication.get().getId());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public boolean create(User user){
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

    private boolean correctPassword(UserAuthentication userAuthentication, String password) {
        boolean res = false;
        if (password.equals(userAuthentication.getPassword())){
            res = true;
        }
        return res;
    }
}
