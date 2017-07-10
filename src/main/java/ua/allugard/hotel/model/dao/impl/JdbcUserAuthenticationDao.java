package ua.allugard.hotel.model.dao.impl;

import ua.allugard.hotel.model.dao.UserAuthenticationDao;
import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.JdbcConnection;
import ua.allugard.hotel.model.entity.UserAuthentication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 01.07.17.
 */
public class JdbcUserAuthenticationDao implements UserAuthenticationDao {

    private ConnectionManager connectionManager;
    private static final int COLUMN_ID_INDEX = 4;
    private static final int COLUMN_LOGIN_INDEX = 1;
    private static final int COLUMN_PASSWORD_INDEX = 2;
    private static final int COLUMN_USER_ROLE_INDEX = 3;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USER_ROLE = "user_role";

    private static final String INSERT_USER_AUTHENTICATION = "INSERT INTO `hotel`.`user_authentications` (`login`, `password`, `user_role`) VALUES (?, ?, ?); ";
    private static final String UPDATE_USER_AUTHENTICATION = "UPDATE `hotel`.`user_authentications` SET `login`=?, `password`=?, `user_role`=? WHERE `id`=?; ";
    private static final String DELETE_USER_AUTHENTICATION = "DELETE FROM `hotel`.`user_authentications` WHERE `id`=?;";


    private static final String FIND_ALL = "SELECT * FROM user_authentications";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String FIND_BY_LOGIN = FIND_ALL + " WHERE login = ?";

    JdbcUserAuthenticationDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static class Holder {
        static final JdbcUserAuthenticationDao INSTANCE = new JdbcUserAuthenticationDao(ConnectionManager.getInstance());
    }

    public static JdbcUserAuthenticationDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<UserAuthentication> find(int id) {
        Optional<UserAuthentication> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = getUserAuthenticationFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<UserAuthentication> findAll() {
        List<UserAuthentication> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = statement.executeQuery();
            result = getUserAuthenticationsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean create(UserAuthentication userAuthentication) {
        int insertedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_USER_AUTHENTICATION,
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(COLUMN_LOGIN_INDEX, userAuthentication.getLogin());
            statement.setString(COLUMN_PASSWORD_INDEX, userAuthentication.getPassword());
            statement.setString(COLUMN_USER_ROLE_INDEX, userAuthentication.getRole().toString());
            insertedRow = statement.executeUpdate();

            userAuthentication.setId(generateId(statement));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedRow > 0;
    }




    @Override
    public boolean update(UserAuthentication userAuthentication) {
        int updatedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_USER_AUTHENTICATION)) {
            statement.setString(COLUMN_LOGIN_INDEX, userAuthentication.getLogin());
            statement.setString(COLUMN_PASSWORD_INDEX, userAuthentication.getPassword());
            statement.setString(COLUMN_USER_ROLE_INDEX, userAuthentication.getRole().toString());
            statement.setInt(COLUMN_ID_INDEX, userAuthentication.getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRow > 0;
    }

    @Override
    public boolean delete(int id) {
        int deletedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_USER_AUTHENTICATION)) {
            statement.setInt(1, id);
            deletedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedRow > 0;
    }

    @Override
    public Optional<UserAuthentication> findUserByLogin(String login) {
        Optional<UserAuthentication> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_LOGIN)){
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            result = getUserAuthenticationFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    private List<UserAuthentication> getUserAuthenticationsFromResultSet(ResultSet resultSet) throws SQLException {
        List<UserAuthentication> result = new ArrayList<>();
        if(resultSet.isBeforeFirst()) {
            while (resultSet.next()) {
                result.add(buildUserAuthentication(resultSet));
            }
        }
        return result;
    }

    private Optional<UserAuthentication> getUserAuthenticationFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<UserAuthentication> result = Optional.empty();
        if(resultSet.isBeforeFirst()){
            while (resultSet.next()) {
                result = Optional.of(buildUserAuthentication(resultSet));
            }
        }
        return result;
    }

    private UserAuthentication buildUserAuthentication(ResultSet resultSet) throws SQLException {
        return new UserAuthentication.Builder()
                .setId(resultSet.getInt(COLUMN_ID))
                .setLogin(resultSet.getString(COLUMN_LOGIN))
                .setPassword(resultSet.getString(COLUMN_PASSWORD))
                .setRole(UserAuthentication.Role.valueOf(resultSet.getString(COLUMN_USER_ROLE).toUpperCase()))
                .build();
    }
}
