package ua.allugard.hotel.model.dao.impl;

import ua.allugard.hotel.model.dao.BookingDao;
import ua.allugard.hotel.model.entity.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 01.07.17.
 */
public class JdbcBookingDao implements BookingDao {

    private Connection connection;
    private static final int COLUMN_ID_INDEX = 7;
    private static final int COLUMN_USERS_ID_INDEX = 1;
    private static final int COLUMN_APARTMENTS_ID_INDEX = 2;
    private static final int COLUMN_DATE_FROM_INDEX = 3;
    private static final int COLUMN_DATE_TO_INDEX = 4;
    private static final int COLUMN_STATUS_INDEX = 5;
    private static final int COLUMN_BILL_ID_INDEX = 6;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE_FROM = "date_from";
    private static final String COLUMN_DATE_TO = "date_to";
    private static final String COLUMN_STATUS = "status";

    private static final String INSERT_BOOKING = "INSERT INTO `hotel`.`bookings` (`users_id`, `apartments_id`, `date_from`, `date_to`, `status`) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_BOOKING = "UPDATE `hotel`.`bookings` SET `users_id`=?, `apartments_id`=?, `date_from`=?, `date_to`=?, `status`=?, `bills_id`=? WHERE `id`=?;";
    private static final String DELETE_BOOKING = "DELETE FROM `hotel`.`bookings` WHERE `id`=?;";


    private static final String FIND_ALL = "SELECT bookings.* FROM bookings";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String FIND_BY_APARTMENT = FIND_ALL + "INNER JOIN apartments ON bookings.apartments_id = apartments.id";
    private static final String FIND_BY_USER = FIND_ALL + "INNER JOIN users ON bookings.users_id = users.id";

    public JdbcBookingDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Booking> find(int id) {
        Optional<Booking> result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = getBookingFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = statement.executeQuery();
            result = getBookingsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean create(Booking booking) {
        int insertedRow = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_BOOKING,
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(COLUMN_USERS_ID_INDEX, booking.getUser().getUserAuthentication().getId());
            statement.setInt(COLUMN_APARTMENTS_ID_INDEX, booking.getApartment().getId());
            statement.setTimestamp(COLUMN_DATE_FROM_INDEX, Timestamp.valueOf(booking.getDateFrom().atStartOfDay()));
            statement.setTimestamp(COLUMN_DATE_TO_INDEX, Timestamp.valueOf(booking.getDateTo().atStartOfDay()));
            statement.setString(COLUMN_STATUS_INDEX, booking.getStatus().toString());
            insertedRow = statement.executeUpdate();
            booking.setId(statement.getGeneratedKeys().getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedRow > 0;

    }

    @Override
    public boolean update(Booking booking) {
        int updatedRow = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(UPDATE_BOOKING)) {
            statement.setInt(COLUMN_USERS_ID_INDEX, booking.getUser().getUserAuthentication().getId());
            statement.setInt(COLUMN_APARTMENTS_ID_INDEX, booking.getApartment().getId());
            statement.setTimestamp(COLUMN_DATE_FROM_INDEX, Timestamp.valueOf(booking.getDateFrom().atStartOfDay()));
            statement.setTimestamp(COLUMN_DATE_TO_INDEX, Timestamp.valueOf(booking.getDateTo().atStartOfDay()));
            statement.setString(COLUMN_STATUS_INDEX, booking.getStatus().toString());
            if (booking.getBill() != null) {
                statement.setInt(COLUMN_BILL_ID_INDEX, booking.getBill().getId());
            }
            statement.setInt(COLUMN_ID_INDEX, booking.getId());
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
                     connection.prepareStatement(DELETE_BOOKING)) {
            statement.setInt(1, id);
            deletedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedRow > 0;
    }

    @Override
    public List<Booking> findByUser(int userId) {
        List<Booking> result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_USER)){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            result = getBookingsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public List<Booking> findByApartment(int apartmentId) {
        List<Booking> result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_APARTMENT)){
            statement.setInt(1, apartmentId);
            ResultSet resultSet = statement.executeQuery();
            result = getBookingsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

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
                .build();
    }

}
