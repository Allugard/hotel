package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.util.constants.Messages;
import ua.allugard.hotel.util.constants.Page;
import ua.allugard.hotel.util.constants.Parameters;
import ua.allugard.hotel.util.Validator;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by allugard on 08.07.17.
 */
public class AddApartmentCommand implements Command {

    private ApartmentService apartmentService;

    AddApartmentCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    private static class Holder {
        static final AddApartmentCommand INSTANCE = new AddApartmentCommand(ApartmentService.getInstance());
    }

    public static AddApartmentCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        Apartment apartment = createApartmentFromRequest(request);

        List<String> errors = validate(apartment);

        if(!errors.isEmpty()){
            setAttributesToRequest(request, apartment, errors);
            return Page.ADD_APARTMENT;
        }

        apartmentService.create(apartment);

        return Page.ADD_APARTMENT;
    }

    private void setAttributesToRequest(HttpServletRequest request, Apartment apartment, List<String> errors) {
        request.setAttribute(Parameters.ERRORS, errors);
        request.setAttribute(Parameters.NUMBER, apartment.getNumber());
        request.setAttribute(Parameters.PRICE, apartment.getPrice());
        request.setAttribute(Parameters.CAPACITY, apartment.getCapacity());
        request.setAttribute(Parameters.APARTMENTS_TYPE, apartment.getApartmentsType().toString());

    }

    private List<String> validate(Apartment apartment) {
        List<String> errors = new ArrayList<>();

        Validator validator = Validator.getInstance();

        if(!validator.validatePrice(apartment.getPrice())){
            errors.add(Messages.INVALID_PRICE);
        }

        if(!validator.validateCapacity(apartment.getCapacity())){
            errors.add(Messages.INVALID_CAPACITY);
        }
        if(!validator.validateNumber(apartment.getNumber())){
            errors.add(Messages.INVALID_NUMBER);
        }

        return errors;
    }

    private Apartment createApartmentFromRequest(HttpServletRequest request) {
        return new Apartment.Builder()
                .setPrice(Integer.parseInt(request.getParameter(Parameters.PRICE)))
                .setCapacity(Integer.parseInt(request.getParameter(Parameters.CAPACITY)))
                .setApartmentsType(Apartment.ApartmentsType.valueOf(request.getParameter(Parameters.APARTMENTS_TYPE).toUpperCase()))
                .setNumber(Integer.parseInt(request.getParameter(Parameters.NUMBER)))
                .build();
    }

}
