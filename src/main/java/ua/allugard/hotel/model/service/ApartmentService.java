package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.ApartmentDao;
import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.dto.BookingDto;
import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 05.07.17.
 */
public class ApartmentService {

    private ConnectionManager connectionManager;
    private DaoFactory daoFactory;

    ApartmentService(ConnectionManager connectionManager, DaoFactory daoFactory) {
        this.connectionManager = connectionManager;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final ApartmentService INSTANCE = new ApartmentService(ConnectionManager.getInstance(), DaoFactory.getInstance());
    }

    public static ApartmentService getInstance() {
        return Holder.INSTANCE;
    }

    public Optional<Apartment> find(int id) throws DaoException {
        Optional<Apartment> apartment;
        apartment = daoFactory.getApartmentDao().find(id);
        return apartment;
    }
/*
    LocalDate dateFrom, LocalDate dateTo, int capacity, Apartment.ApartmentsType apartmentsType
*/
    public List<Apartment> findFreeApartments(BookingDto bookingDto) throws DaoException {
        List<Apartment> apartment;
        apartment = daoFactory.getApartmentDao().findFreeApartments(bookingDto);
        return apartment;
    }

    public List<Apartment> findAll() throws DaoException {
        List<Apartment> apartment;
        apartment = daoFactory.getApartmentDao().findAll();
        return apartment;
    }

    public boolean create(Apartment apartment) throws DaoException {
        boolean created;
        ApartmentDao apartmentDao = daoFactory.getApartmentDao();
        created = apartmentDao.create(apartment);
        return created;
    }

    public boolean delete(int id) throws DaoException {
        boolean deleted;
        deleted = daoFactory.getApartmentDao().delete(id);
        return deleted;
    }


}
