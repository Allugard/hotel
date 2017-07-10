package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Optional;

/**
 * Created by allugard on 08.07.17.
 */
public class UpdateBookingCommand implements Command {

    private BookingService bookingService;
    private static String DATE_PART = "yyyy-mm-dd";
    private static java.text.DateFormat DATE_FORMAT = new java.text.SimpleDateFormat(DATE_PART);


    public UpdateBookingCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public static UpdateBookingCommand getInstance() {
        return new UpdateBookingCommand(BookingService.getInstance());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        Booking s = ((Booking) request.getAttribute("update"));
//        System.out.println("ТАКОЙ ИТЕМ" + s);
        Optional<Booking> booking = bookingService.find(Integer.parseInt(request.getParameter("update")));
        booking.get().setStatus(Booking.Status.REJECTED);
        bookingService.update(booking.get());
//        try {
//            booking = createBookingFromRequest(request);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        bookingService.create(booking);
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
