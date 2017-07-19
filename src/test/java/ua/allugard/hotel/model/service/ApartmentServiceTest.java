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

/*

    private DaoFactory daoFactory;
    private AuthorDao authorDao;
    private AuthorService authorService;

    private Author generateAuthor() {
        return new Author.Builder().setName("Daniel").setSurname("Keyes").setCountry("America").build();
    }

    private List<Author> generateAuthorsList() {
        return Arrays.asList(new Author[] {
                new Author.Builder().setId(new Long(1)).setName("Daniel").setSurname("Keyes").setCountry("America")
                        .build(),
                new Author.Builder().setId(new Long(2)).setName("Stephen").setSurname("King").setCountry("America")
                        .build() });
    }

    private void initObjectsMocking() {
        daoFactory = mock(DaoFactory.class);
        authorDao = mock(AuthorDao.class);
    }

    private void initAuthorService(){
        authorService = new AuthorService(daoFactory);
    }

    private void initObjectsMethodsStubbing() {
        when(daoFactory.createAuthorDao()).thenReturn(authorDao);
    }

    @Test
    //@Ignore
    public void testCreateAuthor() {
        Author author = generateAuthor();

        initObjectsMocking();
        initAuthorService();
        initObjectsMethodsStubbing();

        authorService.createAuthor(author);

        verify(daoFactory).createAuthorDao();
        verify(authorDao).create(author);
    }

    @Test
    //@Ignore
    public void testGetAllAuthors() {
        List<Author> authors = generateAuthorsList();

        initObjectsMocking();
        initAuthorService();
        initObjectsMethodsStubbing();
        when(authorDao.getAll()).thenReturn(authors);

        List<Author> actualAuthors = authorService.getAllAuthors();

        assertEquals(authors, actualAuthors);
        verify(daoFactory).createAuthorDao();
        verify(authorDao).getAll();
    }
}*/
