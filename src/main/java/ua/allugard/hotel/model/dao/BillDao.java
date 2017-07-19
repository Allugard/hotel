package ua.allugard.hotel.model.dao;

import ua.allugard.hotel.model.entity.Bill;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.util.Optional;

/**
 * Created by allugard on 30.06.17.
 */
public interface BillDao extends GenericDao<Bill> {

    Optional<Bill> findByBooking(Booking booking) throws DaoException;
}
