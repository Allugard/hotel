package ua.allugard.hotel.model.dao;

import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.util.List;

/**
 * Created by allugard on 30.06.17.
 */
public interface BookingDao extends GenericDao<Booking> {
    List<Booking> findByUser(int userId) throws DaoException;
    List<Booking> findProcessedBooking(int firstRecord, int recordsPerPage) throws DaoException;
    int getNumberOfPagesForProcessedBookings() throws DaoException;
}
