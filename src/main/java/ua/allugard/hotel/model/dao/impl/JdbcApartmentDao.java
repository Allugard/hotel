package ua.allugard.hotel.model.dao.impl;

import org.apache.log4j.Logger;
import ua.allugard.hotel.model.dao.ApartmentDao;
import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.JdbcConnection;
import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.util.LogMessage;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 01.07.17.
 */
public class JdbcApartmentDao implements ApartmentDao {

    private static final Logger LOGGER = Logger.getLogger(JdbcApartmentDao.class);
    private ConnectionManager connectionManager;
    private static final int COLUMN_ID_INDEX = 5;
    private static final int COLUMN_CAPACITY_INDEX = 1;
    private static final int COLUMN_APARTMENTS_TYPE_INDEX = 2;
    private static final int COLUMN_PRICE_INDEX = 3;
    private static final int COLUMN_NUMBER_INDEX = 4;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_APARTMENTS_TYPE = "apartments_type";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_NUMBER = "number";

    private static final String INSERT_APARTMENT = "INSERT INTO `hotel`.`apartments` (`capacity`, `apartments_type`, `price`, `number`) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_APARTMENT = "UPDATE `hotel`.`apartments` SET `capacity`=?, `apartments_type`=?, `price`=?, `number`=? WHERE `id`=?;";
    private static final String DELETE_APARTMENT = "DELETE FROM `hotel`.`apartments` WHERE `id`=?;";


    private static final String FIND_ALL = "SELECT * FROM apartments";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String FIND_BY_NUMBER = FIND_ALL + " WHERE number = ?";
    private static final String FIND_FREE_NUMBER = FIND_ALL +
                                                         "WHERE apartments.id NOT INT (" +
                                                                            "SELECT bookings.appartments_id " +
                                                                            "FROM apartments " +
                                                                            "INNER JOIN bookings ON bookings.appartments_id = apartments.id" +
                                                                            "WHERE status = 'confirmed' and (? > bookings.date_to OR  ? < bookings.date_from)) " +
                                                          "AND apartments.apartments_type = ? and apartments.capacity = ?";


    JdbcApartmentDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static class Holder {
        static final JdbcApartmentDao INSTANCE = new JdbcApartmentDao(ConnectionManager.getInstance());
    }

    public static JdbcApartmentDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<Apartment> find(int id) {
        Optional<Apartment> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = getApartmentFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcApartmentDao.class.toString() + LogMessage.FIND + e.getMessage());
        }
        return result;
    }

    @Override
    public List<Apartment> findAll() {
        List<Apartment> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = statement.executeQuery();
            result = getApartmentsFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcApartmentDao.class.toString() + LogMessage.FIND_ALL + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean create(Apartment apartment) throws DaoException {
        int insertedRow;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_APARTMENT,
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(COLUMN_CAPACITY_INDEX, apartment.getCapacity());
            statement.setString(COLUMN_APARTMENTS_TYPE_INDEX, apartment.getApartmentsType().toString());
            statement.setInt(COLUMN_PRICE_INDEX, apartment.getPrice());
            statement.setString(COLUMN_NUMBER_INDEX, apartment.getNumber());
            insertedRow = statement.executeUpdate();
            apartment.setId(generateId(statement));
        } catch (SQLException e) {
            LOGGER.info(JdbcApartmentDao.class.toString() + LogMessage.CREATE + e.getMessage());
            throw new DaoException();
        }
        return insertedRow > 0;
    }

    @Override
    public boolean update(Apartment apartment) {
        int updatedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_APARTMENT)) {
            statement.setInt(COLUMN_CAPACITY_INDEX, apartment.getCapacity());
            statement.setString(COLUMN_APARTMENTS_TYPE_INDEX, apartment.getApartmentsType().toString());
            statement.setInt(COLUMN_PRICE_INDEX, apartment.getPrice());
            statement.setString(COLUMN_NUMBER_INDEX, apartment.getNumber());
            statement.setInt(COLUMN_ID_INDEX, apartment.getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(JdbcApartmentDao.class.toString() + LogMessage.UPDATE + e.getMessage());
        }
        return updatedRow > 0;
    }

    @Override
    public boolean delete(int id) {
        int deletedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_APARTMENT)) {
            statement.setInt(1, id);
            deletedRow = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(JdbcApartmentDao.class.toString() + LogMessage.DELETE + e.getMessage());
        }
        return deletedRow > 0;
    }

    @Override
    public Optional<Apartment> findByNumber(String number) {
        Optional<Apartment> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_NUMBER)){
            statement.setString(1, number);
            ResultSet resultSet = statement.executeQuery();
            result = getApartmentFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcApartmentDao.class.toString() + LogMessage.FIND_BY_NUMBER + e.getMessage());
        }
        return result;
    }

    @Override
    public List<Apartment> findFreeNumbers(LocalDate dateFrom, LocalDate dateTo, int capacity, Apartment.ApartmentsType apartmentsType) {
        List<Apartment> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_FREE_NUMBER)){
            statement.setDate(1, Date.valueOf(dateFrom));
            statement.setDate(2, Date.valueOf(dateTo));
            statement.setInt(3, capacity);
            statement.setString(4, apartmentsType.toString());
            ResultSet resultSet = statement.executeQuery();
            result = getApartmentsFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcApartmentDao.class.toString() + LogMessage.FIND_FREE_NUMBER + e.getMessage());
        }
        return result;
    }

    private List<Apartment> getApartmentsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Apartment> result = new ArrayList<>();
        if(resultSet.isBeforeFirst()) {
            while (resultSet.next()) {
                result.add(buildApartment(resultSet));
            }
        }
        return result;
    }

    private Optional<Apartment> getApartmentFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<Apartment> result = Optional.empty();
        if(resultSet.isBeforeFirst()){
            while (resultSet.next()) {
                result = Optional.of(buildApartment(resultSet));
            }
        }
        return result;
    }

    private Apartment buildApartment(ResultSet resultSet) throws SQLException {
        return new Apartment.Builder()
                .setId(resultSet.getInt(COLUMN_ID))
                .setCapacity(resultSet.getInt(COLUMN_CAPACITY))
                .setNumber(resultSet.getString(COLUMN_NUMBER))
                .setApartmentsType(Apartment.ApartmentsType.valueOf(resultSet.getString(COLUMN_APARTMENTS_TYPE).toUpperCase()))
                .setPrice(resultSet.getInt(COLUMN_PRICE))
                .build();
    }


}
