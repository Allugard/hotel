package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Bill;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.time.temporal.ChronoUnit;
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

    private static class Holder {
        static final BookingService INSTANCE = new BookingService(ConnectionManager.getInstance(), DaoFactory.getInstance());
    }

    public static BookingService getInstance() {
        return Holder.INSTANCE;
    }

    public boolean update(Booking booking) throws DaoException {
        return daoFactory.getBookingDao().update(booking);
    }

    public int getNumberOfPagesForProcessedBookings() throws DaoException {
        int numberOfPages = daoFactory.getBookingDao().getNumberOfPagesForProcessedBookings();
        return numberOfPages;
    }

    public void update(List<Booking> bookings) throws DaoException {
        for (Booking booking : bookings) {
            System.out.println(booking);
            if (booking.getStatus().equals(Booking.Status.CONFIRMED)) {
                updateConfirmed(booking);
            } else {
                update(booking);
            }
        }
    }

    private void updateConfirmed(Booking booking) throws DaoException {
        Bill bill = createBill(booking);
        booking.setBill(bill);
        System.out.println(bill);
        try {
            connectionManager.startTransaction();
            daoFactory.getBillDao().create(bill);
            update(booking);
            connectionManager.commit();
        } catch (DaoException e) {
            connectionManager.rollback();
            throw e;
        }
    }

    private Bill createBill(Booking booking) {
        long days = ChronoUnit.DAYS.between(booking.getDateFrom(), booking.getDateTo());
        return new Bill.Builder()
                        .setPrice((int) (days * booking.getApartment().getPrice()))
                        .build();
    }

    public boolean create(Booking booking) throws DaoException {
        boolean created;
        created = daoFactory.getBookingDao().create(booking);
        return created;
    }

    public boolean delete(int id) throws DaoException {
        boolean deleted;
        deleted = daoFactory.getBookingDao().delete(id);
        return deleted;
    }


    public List<Booking> findByUser(int userId) throws DaoException {
        List<Booking> bookings;
        bookings = daoFactory.getBookingDao().findByUser(userId);
        return bookings;
    }

    public List<Booking> findProcessedBookings(int firstRecord, int recordsPerPage) throws DaoException {
        List<Booking> bookings;
        bookings = daoFactory.getBookingDao().findProcessedBooking(firstRecord, recordsPerPage);
        for (Booking booking : bookings) {
            daoFactory.getBillDao().findByBooking(booking);
            Optional<Apartment> apartmentOptional = daoFactory.getApartmentDao().findByBooking(booking);
            if(apartmentOptional.isPresent()){
                booking.setApartment(apartmentOptional.get());
            }
            Optional<User> userOptional = daoFactory.getUserDao().findByBooking(booking);
            if(userOptional.isPresent()){
                booking.setUser(userOptional.get());
            }
        }
        return bookings;
    }

}
