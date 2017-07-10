package ua.allugard.hotel.util;

/**
 * Created by allugard on 08.07.17.
 */
public interface Commands {
    String MAIN = "/";

    String REDIRECT = "redirect";

    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String PROFILE = "/profile";
    String AUTHORIZATION = "/login/authorization";
    String REGISTRATION = "/registration";
    String SIGN_UP = "/registration/signup";
    String ADD_BOOKING_PAGE = "/addBooking";
    String ADD_BOOKING = "/addBooking/add";
    String VIEW_BOOKINGS = "/viewBookings";
    String DELETE_BOOKING = "/deleteBooking";
    String ADD_APARTMENT_PAGE = "/addApartment";
    String ADD_APARTMENT = "/addApartment/add";
    String VIEW_ALL_APARTMENTS = "/allApartments";
    String DELETE_APARTMENT = "/deleteApartment";

}
