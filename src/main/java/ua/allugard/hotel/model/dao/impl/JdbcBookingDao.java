package ua.allugard.hotel.model.dao.impl;

import org.apache.log4j.Logger;
import ua.allugard.hotel.model.dao.BookingDao;
import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.JdbcConnection;
import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.util.constants.LogMessage;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 01.07.17.
 */
public class JdbcBookingDao implements BookingDao {

    private static final Logger LOGGER = Logger.getLogger(JdbcBookingDao.class);
    private ConnectionManager connectionManager;
    private static final int COLUMN_ID_INDEX = 8;
    private static final int COLUMN_USERS_ID_INDEX = 7;
    private static final int COLUMN_APARTMENTS_ID_INDEX = 6;
    private static final int COLUMN_DATE_FROM_INDEX = 1;
    private static final int COLUMN_DATE_TO_INDEX = 2;
    private static final int COLUMN_STATUS_INDEX = 3;
    private static final int COLUMN_BILL_ID_INDEX = 7;
    private static final int COLUMN_PERSONS_INDEX = 4;
    private static final int COLUMN_APARTMENTS_TYPE_INDEX = 5;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE_FROM = "date_from";
    private static final String COLUMN_DATE_TO = "date_to";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_PERSONS = "persons";
    private static final String COLUMN_APARTMENTS_TYPE = "apartments_type";

    private static final String INSERT_BOOKING = "INSERT INTO `hotel`.`bookings` (`date_from`, `date_to`, `status`, `persons`, `apartments_type`, `apartments_id`, `users_id`) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_BOOKING = "UPDATE `hotel`.`bookings` SET `date_from`=?, `date_to`=?, `status`=?, `persons`=?, `apartments_type`=?, `apartments_id`=?, `bills_id`=? WHERE `id`=?;";
    private static final String DELETE_BOOKING = "DELETE FROM `hotel`.`bookings` WHERE `id`=?;";


    private static final String FIND_ALL = "SELECT bookings.* FROM bookings ";
    private static final String FIND_BY_ID = FIND_ALL + "WHERE id = ?";
    private static final String FIND_BY_USER = FIND_ALL + "INNER JOIN users ON bookings.users_id = users.id WHERE users.id = ?";
    private static final String FIND_PROCESSED = FIND_ALL + "WHERE status = \"processed\"";
    private static final String LIMIT = "LIMIT ?,? ";

    JdbcBookingDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static class Holder {
        static final JdbcBookingDao INSTANCE = new JdbcBookingDao(ConnectionManager.getInstance());
    }

    public static JdbcBookingDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<Booking> find(int id) throws DaoException {
        Optional<Booking> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = getBookingFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcBookingDao.class.toString() + LogMessage.FIND + e.getMessage());
            throw new DaoException();
        }
        return result;
    }

    @Override
    public List<Booking> findAll() throws DaoException {
        List<Booking> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = statement.executeQuery();
            result = getBookingsFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcBookingDao.class.toString() + LogMessage.FIND_ALL + e.getMessage());
            throw new DaoException();
        }
        return result;
    }

    @Override
    public boolean create(Booking booking) throws DaoException {
        int insertedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_BOOKING,
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(COLUMN_USERS_ID_INDEX, booking.getUser().getUserAuthentication().getId());
            statement.setTimestamp(COLUMN_DATE_FROM_INDEX, Timestamp.valueOf(booking.getDateFrom().atStartOfDay()));
            statement.setTimestamp(COLUMN_DATE_TO_INDEX, Timestamp.valueOf(booking.getDateTo().atStartOfDay()));
            statement.setString(COLUMN_STATUS_INDEX, booking.getStatus().toString());
            statement.setInt(COLUMN_PERSONS_INDEX, booking.getPersons());
            statement.setInt(COLUMN_APARTMENTS_ID_INDEX, booking.getApartment().getId());
            statement.setString(COLUMN_APARTMENTS_TYPE_INDEX, booking.getApartmentsType().toString());
            insertedRow = statement.executeUpdate();
            booking.setId(generateId(statement));
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info(JdbcBookingDao.class.toString() + LogMessage.CREATE + e.getMessage());
            throw new DaoException();
        }
        return insertedRow > 0;

    }

    @Override
    public boolean update(Booking booking) throws DaoException {
        int updatedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_BOOKING)) {
            statement.setTimestamp(COLUMN_DATE_FROM_INDEX, Timestamp.valueOf(booking.getDateFrom().atStartOfDay()));
            statement.setTimestamp(COLUMN_DATE_TO_INDEX, Timestamp.valueOf(booking.getDateTo().atStartOfDay()));
            statement.setString(COLUMN_STATUS_INDEX, booking.getStatus().toString());
            statement.setInt(COLUMN_PERSONS_INDEX, booking.getPersons());
            statement.setString(COLUMN_APARTMENTS_TYPE_INDEX, booking.getApartmentsType().toString());
            if(booking.getApartment() != null){
                statement.setInt(COLUMN_APARTMENTS_ID_INDEX, booking.getApartment().getId());
            }else {
                statement.setNull(COLUMN_APARTMENTS_ID_INDEX, Types.INTEGER);
            }
            if(booking.getUser() != null) {
                statement.setInt(COLUMN_USERS_ID_INDEX, booking.getUser().getUserAuthentication().getId());
            } else {
                statement.setNull(COLUMN_USERS_ID_INDEX, Types.INTEGER);
            }
            if (booking.getBill() != null) {
                statement.setInt(COLUMN_BILL_ID_INDEX, booking.getBill().getId());
            } else {
                statement.setNull(COLUMN_BILL_ID_INDEX, Types.INTEGER);
            }
            statement.setInt(COLUMN_ID_INDEX, booking.getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(JdbcBookingDao.class.toString() + LogMessage.UPDATE + e.getMessage());
            throw new DaoException();
        }
        return updatedRow > 0;

    }

    @Override
    public boolean delete(int id) throws DaoException {
        int deletedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_BOOKING)) {
            statement.setInt(1, id);
            deletedRow = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(JdbcBookingDao.class.toString() + LogMessage.DELETE + e.getMessage());
            throw new DaoException();
        }
        return deletedRow > 0;
    }

    @Override
    public List<Booking> findByUser(int userId) throws DaoException {
        List<Booking> result = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_USER)){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            result = getBookingsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info(JdbcBookingDao.class.toString() + LogMessage.FIND_BY_USER + e.getMessage());
            throw new DaoException();
        }
        return result;
    }

    @Override
    public List<Booking> findProcessedBooking(int firstRecord, int recordsPerPage) throws DaoException {
        List<Booking> results = null;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_PROCESSED + LIMIT)){
            statement.setInt(1, firstRecord);
            statement.setInt(2, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            results = getBookingsFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcBookingDao.class.toString() + LogMessage.FIND_PROCESSED_BOOKINGS + e.getMessage());
            throw new DaoException();
        }
        return results;
    }

    @Override
    public int getNumberOfPagesForProcessedBookings() throws DaoException {
        int results = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_PROCESSED,
                             ResultSet.TYPE_SCROLL_INSENSITIVE,
                             ResultSet.CONCUR_READ_ONLY)){
            ResultSet resultSet = statement.executeQuery();
            results = getRowCount(resultSet);
        } catch (SQLException e) {
            LOGGER.info(JdbcBookingDao.class.toString() + LogMessage.GET_NUMBER_OF_PAGES_FOR_PROCESSED_BOOKINGS + e.getMessage());
            throw new DaoException();
        }
        return results;
    }

    private int getRowCount(ResultSet resultSet) throws SQLException {
        int rowCount = 0;
        if(resultSet.isBeforeFirst()) {
            resultSet.last();
            rowCount = resultSet.getRow();
        }
        return rowCount;
    }

    private List<Booking> getBookingsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Booking> result = new ArrayList<>();
        if(resultSet.isBeforeFirst()) {
            while (resultSet.next()) {
                result.add(buildBooking(resultSet));
            }
        }
        return result;
    }

    private Optional<Booking> getBookingFromResultSet(ResultSet resultSet) throws SQLException {
        Optional<Booking> result = Optional.empty();
        if(resultSet.isBeforeFirst()){
            while (resultSet.next()) {
                result = Optional.of(buildBooking(resultSet));
            }
        }
        return result;
    }

    private Booking buildBooking(ResultSet resultSet) throws SQLException {
        return new Booking.Builder()
                .setId(resultSet.getInt(COLUMN_ID))
                .setStatus(Booking.Status.valueOf(resultSet.getString(COLUMN_STATUS).toUpperCase()))
                .setDateFrom(resultSet.getTimestamp(COLUMN_DATE_FROM))
                .setDateTo(resultSet.getTimestamp(COLUMN_DATE_TO))
                .setApartmentsType(Apartment.ApartmentsType.valueOf(resultSet.getString(COLUMN_APARTMENTS_TYPE).toUpperCase()))
                .setPersons(resultSet.getInt(COLUMN_PERSONS))
                .build();
    }

}
