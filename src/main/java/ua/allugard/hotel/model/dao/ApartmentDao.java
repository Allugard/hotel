package ua.allugard.hotel.model.dao;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 30.06.17.
 */
public interface ApartmentDao extends GenericDao<Apartment> {
    Optional<Apartment> findByNumber(String number) throws DaoException;
    List<Apartment> findFreeApartments(Booking booking) throws DaoException;
}
