package ua.allugard.hotel.model.dao;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 30.06.17.
 */
public interface ApartmentDao extends GenericDao<Apartment> {
    Optional<Apartment> findByNumber(String number);
    List<Apartment> findFreeApartments(Booking booking);
}
