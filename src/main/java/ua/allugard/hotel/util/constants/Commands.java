package ua.allugard.hotel.util.constants;

/**
 * Created by allugard on 08.07.17.
 */
public interface Commands {
    String MAIN = "/";

    String REDIRECT = "redirect";
    String LOGOUT = "logout";
    String SIGN_IN = "signin";
    String SIGN_UP = "signup";

    String ADD_BOOKING = "addBooking";
    String BOOKINGS_BY_USER = "bookingsByUser";
    String DELETE_BOOKING = "deleteBooking";

    String ADD_APARTMENT = "addApartment";
    String ALL_APARTMENTS = "allApartments";
    String DELETE_APARTMENT = "deleteApartment";

    String PROCESSED_BOOKINGS = "processedBookings";
    String UPDATE_BOOKING = "updateBooking";


}
