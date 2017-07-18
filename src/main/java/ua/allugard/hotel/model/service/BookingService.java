package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 05.07.17.
 */
public class BookingService {

    private ConnectionManager connectionManager;
    private DaoFactory daoFactory;

    BookingService(ConnectionManager connectionManager, DaoFactory daoFactory) {
        this.connectionManager = connectionManager;
        this.daoFactory = daoFactory;
    }

    public Optional<Booking> find(int id) {
        return daoFactory.getBookingDao().find(id);
    }

    public boolean update(Booking booking) {
        return daoFactory.getBookingDao().update(booking);
    }

    public int getNumberOfPagesForProcessedBookings() {
        int numberOfPages = daoFactory.getBookingDao().getNumberOfPagesForProcessedBookings();
        return numberOfPages;
    }

    private static class Holder {
        static final BookingService INSTANCE = new BookingService(ConnectionManager.getInstance(), DaoFactory.getInstance());
    }

    public static BookingService getInstance() {
        return Holder.INSTANCE;
    }

    public boolean create(Booking booking) throws DaoException {
        boolean created;
        created = daoFactory.getBookingDao().create(booking);
        return created;
    }

    public boolean delete(int id){
        boolean deleted;
        deleted = daoFactory.getBookingDao().delete(id);
        return deleted;
    }


    public List<Booking> findByUser(int userId){
        List<Booking> bookings;
        bookings= daoFactory.getBookingDao().findByUser(userId);
        return bookings;
    }

    public List<Booking> findProcessedBookings(int firstRecord, int recordsPerPage){
        List<Booking> bookings;
        bookings = daoFactory.getBookingDao().findProcessedBooking(firstRecord, recordsPerPage);
        return bookings;
    }

}
