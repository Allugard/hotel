package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.constants.Messages;
import ua.allugard.hotel.util.constants.Parameters;
import ua.allugard.hotel.util.Validator;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by allugard on 08.07.17.
 */
public class UpdateBookingCommand implements Command {

    private static final java.lang.String SPACE = " ";
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        List<Booking> bookings = createBookingsFromRequest(request);

        List<String> errors = validate(bookings);

        if(!errors.isEmpty()){
            setErrorsToRequest(request, errors);
            return ProcessedBookingsCommand.getInstance().execute(request, response);
        }

        System.out.println(bookings);
        bookingService.update(bookings);

        return ProcessedBookingsCommand.getInstance().execute(request, response);
    }

    private List<String> validate(List<Booking> bookings) {
        List<String> errors = new ArrayList<>();

        Validator validator = Validator.getInstance();

        if(!validator.validateNumberAndDateUpdatedBookings(bookings)){
            errors.add(Messages.INVALID_DATA);
        }

        return errors;
    }

    private void setErrorsToRequest(HttpServletRequest request, List<String> errors) {
        request.setAttribute(Parameters.ERRORS, errors);
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

            if (request.getParameterValues(Parameters.STATUS)[i].equals(Booking.Status.REJECTED.toString())) {
                booking.setStatus(Booking.Status.REJECTED);
                bookings.add(booking);
            }

            if (request.getParameterValues(Parameters.STATUS)[i].equals(Booking.Status.CONFIRMED.toString())) {
                String[] apartmentParameters = request.getParameterValues(Parameters.STATUS)[i].split(SPACE);
                booking.setStatus(Booking.Status.CONFIRMED);
                booking.setApartment(new Apartment.Builder()
                        .setId(Integer.parseInt(request.getParameter("apartmentsId")))
                        .setPrice(Integer.parseInt(request.getParameter("apartmentsPrice")))
                        .build());

                bookings.add(booking);
            }
        }
        return bookings;
    }
}
