package ua.allugard.hotel.model.dao.impl;

import ua.allugard.hotel.model.dao.UserDao;
import ua.allugard.hotel.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 01.07.17.
 */
public class JdbcUserDao implements UserDao {

    private Connection connection;
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


    private static final String FIND_ALL = "SELECT * FROM user";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String FIND_BY_FULL_NAME = FIND_ALL + " WHERE last_name = ? AND first_name = ?";

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> find(int id) {
        Optional<User> result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = statement.executeQuery();
            result = getUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean create(User user) {
        int insertedRow = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_USER)) {
            statement.setInt(COLUMN_ID_INDEX, user.getUserAuthentication().getId());
            statement.setString(COLUMN_FIRST_NAME_INDEX, user.getFirstName());
            statement.setString(COLUMN_LAST_NAME_INDEX, user.getLastName());
            statement.setString(COLUMN_PHONE_INDEX, user.getPhone());
            insertedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedRow > 0;
    }

    @Override
    public boolean update(User user) {
        int updatedRow = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(UPDATE_USER)) {
            statement.setInt(COLUMN_ID_INDEX, user.getUserAuthentication().getId());
            statement.setString(COLUMN_FIRST_NAME_INDEX, user.getFirstName());
            statement.setString(COLUMN_LAST_NAME_INDEX, user.getLastName());
            statement.setString(COLUMN_PHONE_INDEX, user.getPhone());
            statement.setInt(5, user.getUserAuthentication().getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRow > 0;
    }

    @Override
    public boolean delete(int id) {
        int deletedRow = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            deletedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedRow > 0;
    }

    @Override
    public List<User> findUserByFullName(String firstName, String lastName) {
        List<User> result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_FULL_NAME)){
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            ResultSet resultSet = statement.executeQuery();
            result = getUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
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
