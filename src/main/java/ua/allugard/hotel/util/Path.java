package ua.allugard.hotel.util;

/**
 * Created by allugard on 08.07.17.
 */
public interface Path {
    String MAIN = "/";
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String PROFILE = "/profile";
    String REGISTRATION = "/registration";
    String SIGN_UP = "/signup";
    String SIGN_IN = "/login/signin";
    String ADD_BOOKING_PAGE = "/profile/addBooking";
    String ADD_BOOKING = "/profile/addBooking/add";
    String DELETE_BOOKING = "/profile/bookings/delete";
    String UPDATE_BOOKING = "/profile/processedBookings/update";
    String ADD_APARTMENT_PAGE = "/profile/addApartment";
    String ADD_APARTMENT = "/profile/addApartment/add";
    String ALL_APARTMENTS = "/profile/apartments";
    String DELETE_APARTMENT = "/deleteApartment";
    String PROCESSED_BOOKINGS = "/profile/processedBookings";
    String BOOKINGS_BY_USER = "/profile/bookings";
}
