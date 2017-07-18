package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;
import ua.allugard.hotel.util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 08.07.17.
 */
public class UpdateBookingCommand implements Command {

    private BookingService bookingService;

    UpdateBookingCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private static class Holder {
        static final UpdateBookingCommand INSTANCE = new UpdateBookingCommand(BookingService.getInstance());
    }

    public static UpdateBookingCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        Booking s = ((Booking) request.getAttribute("update"));
//        System.out.println("ТАКОЙ ИТЕМ" + s);
//        Optional<Booking> booking = bookingService.find(Integer.parseInt(request.getParameter("update")));

        List<Booking> bookings = createBookingsFromRequest(request);

        System.out.println(bookings);
//        bookingService.update(booking);
//        System.out.println("UPDAAAAAAAAAAAAAGE");
//        System.out.println(request.getParameter("update"));
//        System.out.println(Arrays.toString(request.getParameterValues("update")));
//        booking.get().setStatus(Booking.Status.REJECTED);

//        bookingService.update(booking.get());

//        try {
//            booking = createBookingFromRequest(request);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        bookingService.create(booking);
        return ProcessedBookingsCommand.getInstance().execute(request, response);
    }

    private List<Booking> createBookingsFromRequest(HttpServletRequest request) {
        List<Booking> bookings = new ArrayList<>();
        for (int i = 0; i < request.getParameterValues(Parameters.ID).length; i++) {
            Booking booking = new Booking.Builder()
                    .setId(Integer.parseInt(request.getParameterValues(Parameters.ID)[i]))
                    .setDateFrom(LocalDate.parse(request.getParameterValues(Parameters.DATE_FROM)[i]))
                    .setDateTo(LocalDate.parse(request.getParameterValues(Parameters.DATE_TO)[i]))
                    .setPersons(Integer.parseInt(request.getParameterValues(Parameters.PERSONS)[i]))
                    .setApartmentsType(Apartment.ApartmentsType.valueOf(request.getParameterValues(Parameters.APARTMENTS_TYPE)[i].toUpperCase()))
                    .build();

            if(request.getParameterValues(Parameters.STATUS)[i].equals(Booking.Status.REJECTED.toString())){
                booking.setStatus(Booking.Status.REJECTED);
            } else {
                System.out.println(request.getParameterValues(Parameters.STATUS)[i]);
                booking.setStatus(Booking.Status.CONFIRMED);
                booking.setApartment(new Apartment.Builder()
                        .setId(Integer.parseInt(request.getParameterValues(Parameters.STATUS)[i]))
                        .build());
            }
            System.out.println(booking);
            bookings.add(booking);
        }
/*        Booking booking = new Booking.Builder()
                .setId(Integer.parseInt(request.getParameter(Parameters.ID)))
                .setDateFrom(LocalDate.parse(request.getParameter(Parameters.DATE_FROM)))
                .setDateTo(LocalDate.parse(request.getParameter(Parameters.DATE_TO)))
                .setPersons(Integer.parseInt(request.getParameter(Parameters.PERSONS)))
                .setApartmentsType(Apartment.ApartmentsType.valueOf(request.getParameter(Parameters.APARTMENTS_TYPE).toUpperCase()))
                .setStatus(Booking.Status.PROCESSED)
                .setUser(((User) request.getSession().getAttribute(Parameters.USER)))
                .build();

        if(request.getParameter(Parameters.STATUS).equals(Booking.Status.REJECTED.toString())){
            booking.setStatus(Booking.Status.REJECTED);
        } else {
            booking.setStatus(Booking.Status.CONFIRMED);
            booking.setApartment(new Apartment.Builder()
                    .setId(Integer.parseInt(request.getParameter(Parameters.STATUS)))
                    .build());
        }*/

        return bookings;
    }

}
