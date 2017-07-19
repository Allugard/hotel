package ua.allugard.hotel.util.constants;

/**
 * Created by allugard on 08.07.17.
 */
public interface Path {
    String MAIN = "/";
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String PROFILE = "/profile";
    String REGISTRATION = "/registration";
    String APARTMENTS = "/apartments";
    String FIND = "/apartments/find";
    String SIGN_UP = "/registration/signup";
    String SIGN_IN = "/login/signin";
    String ADD_BOOKING_PAGE = "/profile/addBooking";
    String ADD_BOOKING = "/profile/addBooking/add";
    String DELETE_BOOKING = "/profile/bookings/delete";
    String UPDATE_BOOKING = "/profile/processedBookings/update";
    String ADD_APARTMENT_PAGE = "/profile/addApartment";
    String ADD_APARTMENT = "/profile/addApartment/add";
    String ALL_APARTMENTS = "/profile/apartments";
    String DELETE_APARTMENT = "/profile/apartments/delete";
    String PROCESSED_BOOKINGS = "/profile/processedBookings";
    String BOOKINGS_BY_USER = "/profile/bookings";
    String FIND_FREE_APARTMENTS = "/profile/addBooking/find";
}
