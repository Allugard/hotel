package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.constants.Messages;
import ua.allugard.hotel.util.constants.Page;
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
public class AddBookingCommand implements Command {

    private BookingService bookingService;

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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        Booking booking = createBookingFromRequest(request);

        List<String> errors = validate(booking);

        if(!errors.isEmpty()){
            setAttributesToRequest(request, booking, errors);
            return Page.ADD_BOOKING;
        }

        bookingService.create(booking);

        return Page.PROFILE;
    }

    private void setAttributesToRequest(HttpServletRequest request, Booking booking, List<String> errors) {
        request.setAttribute(Parameters.ERRORS, errors);
        request.setAttribute(Parameters.DATE_FROM, booking.getDateFrom());
        request.setAttribute(Parameters.DATE_TO, booking.getDateTo());
        request.setAttribute(Parameters.PERSONS, booking.getPersons());
        request.setAttribute(Parameters.APARTMENTS_TYPE, booking.getApartmentsType().toString());
    }


    private List<String> validate(Booking booking) {
        List<String> errors = new ArrayList<>();

        Validator validator = Validator.getInstance();

        if(!validator.validateDate(booking.getDateFrom(), booking.getDateTo())){
            errors.add(Messages.INVALID_DATE);
        }

        if(!validator.validateCapacity(booking.getPersons())){
            errors.add(Messages.INVALID_PERSONS);
        }

        return errors;
    }

    private Booking createBookingFromRequest(HttpServletRequest request) {
        return new Booking.Builder()
                .setDateFrom(LocalDate.parse(request.getParameter(Parameters.DATE_FROM)))
                .setDateTo(LocalDate.parse(request.getParameter(Parameters.DATE_TO)))
                .setPersons(Integer.parseInt(request.getParameter(Parameters.PERSONS)))
                .setApartmentsType(Apartment.ApartmentsType.valueOf(request.getParameter(Parameters.APARTMENTS_TYPE).toUpperCase()))
                .setStatus(Booking.Status.PROCESSED)
                .setUser(((User) request.getSession().getAttribute(Parameters.USER)))
                .build();
    }

}
