package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.entity.UserAuthentication;
import ua.allugard.hotel.util.exceptions.DaoException;

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
            updated = daoFactory.getUserDao().update(user);
            updated = daoFactory.getUserAuthenticationDao().update(user.getUserAuthentication());
            connectionManager.commit();
        }catch (Exception e){
            connectionManager.rollback();
        }
        return updated;
    }


    public Optional<User> findUserByLoginPassword(String login, String password){
        Optional<User> user = Optional.empty();
        Optional<UserAuthentication> userAuthentication;
        userAuthentication = daoFactory.getUserAuthenticationDao().findUserByLogin(login);
        if (userAuthentication.isPresent() && correctPassword(userAuthentication.get(), password)) {
            user = daoFactory.getUserDao().find(userAuthentication.get().getId());
            user.get().setUserAuthentication(userAuthentication.get());
        }
        return user;
    }

    public boolean create(User user) throws DaoException {
        boolean created;
        connectionManager.startTransaction();
        daoFactory.getUserAuthenticationDao().create(user.getUserAuthentication());
        created = daoFactory.getUserDao().create(user);
        connectionManager.commit();
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
