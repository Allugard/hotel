package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.entity.UserAuthentication;
import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.model.service.UserService;
import ua.allugard.hotel.util.Page;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by allugard on 08.07.17.
 */
public class AddBookingCommand implements Command {

    private BookingService bookingService;
    private static String DATE_PART = "yyyy-mm-dd";
    private static java.text.DateFormat DATE_FORMAT = new java.text.SimpleDateFormat(DATE_PART);


    AddBookingCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private static class Holder {
        static final AddBookingCommand INSTANCE = new AddBookingCommand(BookingService.getInstance());
    }

    public static AddBookingCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Booking booking = null;
        try {
            booking = createBookingFromRequest(request);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            bookingService.create(booking);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return Page.PROFILE;
    }

    private Booking createBookingFromRequest(HttpServletRequest request) throws ParseException {
        return new Booking.Builder()
                .setDateFrom(DATE_FORMAT.parse(request.getParameter("dateFrom")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .setDateTo(DATE_FORMAT.parse(request.getParameter("dateTo")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .setPersons(Integer.parseInt(request.getParameter("persons")))
                .setApartmentsType(Apartment.ApartmentsType.valueOf(request.getParameter("apartmentsType").toUpperCase()))
                .setStatus(Booking.Status.PROCESSED)
                .setUser(((User) request.getSession().getAttribute("user")))
                .build();
    }

}
