package ua.allugard.hotel.model.dao.impl;

import org.apache.log4j.Logger;
import ua.allugard.hotel.model.dao.UserDao;
import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.JdbcConnection;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.util.constants.LogMessage;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 01.07.17.
 */
public class JdbcUserDao implements UserDao {

    private ConnectionManager connectionManager;
    private static final Logger LOGGER = Logger.getLogger(JdbcUserDao.class);
    private static final int COLUMN_ID_INDEX = 1;
    private static final int COLUMN_FIRST_NAME_INDEX = 2;
    private static final int COLUMN_LAST_NAME_INDEX = 3;
    private static final int COLUMN_PHONE_INDEX = 4;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_PHONE = "phone";

    private static final String INSERT_USER = "INSERT INTO `hotel`.`users` (`id`, `first_name`, `last_name`, `phone`) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_USER = "UPDATE `hotel`.`users` SET `id`=?, `first_name`=?, `last_name`=?, `phone`=? WHERE `id`=?; ";
    private static final String DELETE_USER = "DELETE FROM `hotel`.`users` WHERE `id`=?;";


    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String FIND_BY_FULL_NAME = FIND_ALL + " WHERE last_name = ? AND first_name = ?";

    JdbcUserDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static class Holder {
        static final JdbcUserDao INSTANCE = new JdbcUserDao(ConnectionManager.getInstance());
    }

    public static JdbcUserDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<User> find(int id) throws DaoException {
        Optional<User> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcUserDao.class.toString() + LogMessage.FIND + e.getMessage());
            throw new DaoException();

        }
        return result;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = statement.executeQuery();
            result = getUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcUserDao.class.toString() + LogMessage.FIND_ALL + e.getMessage());
            throw new DaoException();
        }
        return result;
    }

    @Override
    public boolean create(User user) throws DaoException {
        int insertedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_USER)) {
            statement.setInt(COLUMN_ID_INDEX, user.getUserAuthentication().getId());
            statement.setString(COLUMN_FIRST_NAME_INDEX, user.getFirstName());
            statement.setString(COLUMN_LAST_NAME_INDEX, user.getLastName());
            statement.setString(COLUMN_PHONE_INDEX, user.getPhone());
            insertedRow = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(JdbcUserDao.class.toString() + LogMessage.CREATE + e.getMessage());
            throw new DaoException();
        }
        return insertedRow > 0;
    }

    @Override
    public boolean update(User user) throws DaoException {
        int updatedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_USER)) {
            statement.setInt(COLUMN_ID_INDEX, user.getUserAuthentication().getId());
            statement.setString(COLUMN_FIRST_NAME_INDEX, user.getFirstName());
            statement.setString(COLUMN_LAST_NAME_INDEX, user.getLastName());
            statement.setString(COLUMN_PHONE_INDEX, user.getPhone());
            statement.setInt(5, user.getUserAuthentication().getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(JdbcUserDao.class.toString() + LogMessage.UPDATE + e.getMessage());
            throw new DaoException();
        }
        return updatedRow > 0;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        int deletedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            deletedRow = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(JdbcUserDao.class.toString() + LogMessage.DELETE + e.getMessage());
            throw new DaoException();
        }
        return deletedRow > 0;
    }

    @Override
    public List<User> findUserByFullName(String firstName, String lastName) throws DaoException {
        List<User> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_FULL_NAME)){
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            ResultSet resultSet = statement.executeQuery();
            result = getUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcUserDao.class.toString() + LogMessage.FIND_BY_FULL_NAME + e.getMessage());
            throw new DaoException();
        }
        return result;
    }

    private List<User> getUsersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> result = new ArrayList<>();
        if(resultSet.isBeforeFirst()) {
            while (resultSet.next()) {
                result.add(buildUser(resultSet));
            }
        }
        return result;
    }

    private Optional<User> getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<User> result = Optional.empty();
        if(resultSet.isBeforeFirst()){
            while (resultSet.next()) {
                result = Optional.of(buildUser(resultSet));
            }
        }
        return result;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setFirstName(resultSet.getString(COLUMN_FIRST_NAME))
                .setLastName(resultSet.getString(COLUMN_LAST_NAME))
                .setPhone(resultSet.getString(COLUMN_PHONE))
                .build();
    }
}
