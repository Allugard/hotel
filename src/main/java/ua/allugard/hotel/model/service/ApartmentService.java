package ua.allugard.hotel.model.service;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.dao.util.DatabaseConnection;
import ua.allugard.hotel.model.entity.Apartment;

import java.time.LocalDate;
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

    Optional<Apartment> find(int id){
        Optional<Apartment> apartment;
        apartment = daoFactory.createApartmentDao().find(id);
        return apartment;
    }

    Optional<Apartment> findByNumber(String number){
        Optional<Apartment> apartment;
        apartment = daoFactory.createApartmentDao().findByNumber(number);
        return apartment;
    }

    List<Apartment> findFreeNumbers(LocalDate dateFrom, LocalDate dateTo, int capacity, Apartment.ApartmentsType apartmentsType){
        List<Apartment> apartment;
        apartment = daoFactory.createApartmentDao().findFreeNumbers(dateFrom, dateTo, capacity, apartmentsType);
        return apartment;
    }
    List<Apartment> findAll(){
        List<Apartment> apartment;
        apartment = daoFactory.createApartmentDao().findAll();
        return apartment;
    }

    boolean create(Apartment apartment){
        boolean created;
        created = daoFactory.createApartmentDao().create(apartment);
        return created;
    }

    boolean update(Apartment apartment){
        boolean updated;
        updated = daoFactory.createApartmentDao().update(apartment);
        return updated;
    }

    boolean delete(Apartment apartment){
        boolean deleted;
            //TODO add method deleteByApartmentId in BookingDao
            deleted = daoFactory.createApartmentDao().delete(apartment.getId());
        return deleted;
    }


}
