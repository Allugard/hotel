package ua.allugard.hotel.model.dao;

import ua.allugard.hotel.model.entity.Booking;

import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 30.06.17.
 */
public interface BookingDao extends GenericDao<Booking> {
    List<Booking> findByUser(int userId);
    List<Booking> findProcessedBooking();
}
