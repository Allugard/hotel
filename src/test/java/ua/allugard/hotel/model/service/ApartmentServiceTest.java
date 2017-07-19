package ua.allugard.hotel.model.service;

import org.junit.Test;
import ua.allugard.hotel.model.dao.ApartmentDao;
import ua.allugard.hotel.model.dao.impl.JdbcApartmentDao;
import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.util.exceptions.DaoException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by allugard on 15.07.17.
 */
public class ApartmentServiceTest {

    private DaoFactory daoFactory;
    private ConnectionManager connectionManager;
    private ApartmentService apartmentService;
    private ApartmentDao apartmentDao;

    private void init(){
        daoFactory = mock(DaoFactory.class);
        connectionManager = mock(ConnectionManager.class);
        apartmentDao = mock(JdbcApartmentDao.class);
        apartmentService = new ApartmentService(connectionManager, daoFactory);
    }

    private Apartment buildApartment() {
        return new Apartment.Builder()
                .setId(1)
                .setCapacity(4)
                .setNumber(315)
                .setApartmentsType(Apartment.ApartmentsType.STANDART)
                .setPrice(350)
                .build();
    }

    private List<Apartment> buildApartments() {
        return Arrays.asList(buildApartment(), buildApartment());
    }

    private void stubDaoFactory() {
        when(daoFactory.getApartmentDao()).thenReturn(apartmentDao);
    }

    private void stubForFindAll(List<Apartment> apartments) throws DaoException {
        stubDaoFactory();
        when(apartmentDao.findAll()).thenReturn(apartments);
    }




    @Test
    public void testCreateAuthor() throws DaoException {
        Apartment apartment = buildApartment();
        init();
        stubDaoFactory();

        apartmentService.create(apartment);

        verify(daoFactory).getApartmentDao();
        verify(apartmentDao).create(apartment);
    }

    @Test
    public void testFindAll() throws DaoException {
        List<Apartment> apartments = buildApartments();
        init();
        stubForFindAll(apartments);

        List<Apartment> actualApartments = apartmentService.findAll();

        assertEquals(apartments, actualApartments);
        verify(daoFactory).getApartmentDao();
        verify(apartmentDao).findAll();
    }




}
