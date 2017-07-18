package ua.allugard.hotel.util;

/**
 * Created by allugard on 11.07.17.
 */
public interface LogMessage {
    String CREATE_USER = "Create user ";
    String CREATED_SUCCESSFULLY = "Created successfully";
    String FIND ="find()" ;
    String FIND_ALL = "findAll()";
    String CREATE = "create()";
    String UPDATE = "update()";
    String DELETE = "delete()";
    String FIND_BY_FULL_NAME = "findUserByFullName()";
    String FIND_BY_USER = "findByUser()";
    String FIND_PROCESSED_BOOKINGS = "findProcessedBookings()";
    String FIND_BY_NUMBER = "findByNumber()";
    String FIND_FREE_NUMBER = "findFreeNumber()";
    String FIND_USER_BY_LOGIN = "Find user by login";
    String FIND_SUCCESSFULLY = "Find successfully";
    String CREATE_BOOKING = "Create booking";
    String DELETE_BOOKING = "Delete booking";
    String FIND_BOOKING_BY_USER = "Find booking by user";
    String FIND_PROCESSED_BOOKINGS_IN_SERVICE_LAYER = "Find processed bookings";
    String GET_NUMBER_OF_PAGES_FOR_PROCESSED_BOOKINGS = "getNumberOfPagesForProcessedBookings()";
}
