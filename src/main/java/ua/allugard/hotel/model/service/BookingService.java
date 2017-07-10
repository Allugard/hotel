package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.entity.Booking;

import java.util.ArrayList;
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
        return daoFactory.createBookingDao().find(id);
    }

    public boolean update(Booking booking) {
        return daoFactory.createBookingDao().update(booking);
    }

    private static class Holder {
        static final BookingService INSTANCE = new BookingService(ConnectionManager.getInstance(), DaoFactory.getInstance());
    }

    public static BookingService getInstance() {
        return Holder.INSTANCE;
    }

    public boolean create(Booking booking){
        boolean created;
        created = daoFactory.createBookingDao().create(booking);
        return created;
    }

    public boolean delete(int id){
        boolean deleted;
        deleted = daoFactory.createBookingDao().delete(id);
        return deleted;
    }


    public List<Booking> findByUser(int userId){
        List<Booking> bookings;
        bookings= daoFactory.createBookingDao().findByUser(userId);
        return bookings;
    }

    public List<Booking> findProcessedBookings(){
        List<Booking> bookings;
        bookings = daoFactory.createBookingDao().findProcessedBooking();
        return bookings;
    }

}
