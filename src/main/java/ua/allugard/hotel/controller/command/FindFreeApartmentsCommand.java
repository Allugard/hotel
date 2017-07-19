package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.dto.BookingDto;
import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.service.ApartmentService;
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
public class FindFreeApartmentsCommand implements Command {

    private ApartmentService apartmentService;

    FindFreeApartmentsCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    private static class Holder {
        static final FindFreeApartmentsCommand INSTANCE = new FindFreeApartmentsCommand(ApartmentService.getInstance());
    }

    public static FindFreeApartmentsCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        BookingDto bookingDto = createBookingFromRequest(request);
        String page = createPageFromRequest(request);
        List<String> errors = validate(bookingDto);

        if(!errors.isEmpty()){
            setAttributesToRequestWithErrors(request, bookingDto, errors);
            return page;
        }

        List<Apartment> apartments = apartmentService.findFreeApartments(bookingDto);

        setAttributesToRequest(request, bookingDto, apartments);

        return page;
    }

    private String createPageFromRequest(HttpServletRequest request) {
        return request.getParameter(Parameters.PAGE);
    }

    private void setAttributesToRequest(HttpServletRequest request, BookingDto bookingDto, List<Apartment> apartments) {
        setInputedAttributesToRequest(request, bookingDto);
        setApartmentsToRequest(request, apartments);
    }

    private void setApartmentsToRequest(HttpServletRequest request, List<Apartment> apartments) {
        request.setAttribute(Parameters.FREE_NUMBERS, apartments);
    }

    private void setAttributesToRequestWithErrors(HttpServletRequest request, BookingDto bookingDto, List<String> errors) {
        setInputedAttributesToRequest(request, bookingDto);
        request.setAttribute(Parameters.ERRORS, errors);
    }

    private void setInputedAttributesToRequest(HttpServletRequest request, BookingDto bookingDto) {
        request.setAttribute(Parameters.DATE_FROM, bookingDto.getDateFrom());
        request.setAttribute(Parameters.DATE_TO, bookingDto.getDateTo());
        request.setAttribute(Parameters.PERSONS, bookingDto.getPersons());
        request.setAttribute(Parameters.APARTMENTS_TYPE, bookingDto.getApartmentsType().toString());
    }


    private List<String> validate(BookingDto booking) {
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

    private BookingDto createBookingFromRequest(HttpServletRequest request) {
        return new BookingDto.Builder()
                .setDateFrom(LocalDate.parse(request.getParameter(Parameters.DATE_FROM)))
                .setDateTo(LocalDate.parse(request.getParameter(Parameters.DATE_TO)))
                .setPersons(Integer.parseInt(request.getParameter(Parameters.PERSONS)))
                .setApartmentsType(Apartment.ApartmentsType.valueOf(request.getParameter(Parameters.APARTMENTS_TYPE).toUpperCase()))
                .build();
    }

}
