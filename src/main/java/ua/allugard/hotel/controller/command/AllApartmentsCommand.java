package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.util.constants.Page;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by allugard on 10.07.17.
 */
public class AllApartmentsCommand implements Command{
    private ApartmentService apartmentService;

    AllApartmentsCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    private static class Holder {
        static final AllApartmentsCommand INSTANCE = new AllApartmentsCommand(ApartmentService.getInstance());
    }

    public static AllApartmentsCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        List<Apartment> apartments;
        apartments = apartmentService.findAll();
        request.setAttribute("apartments", apartments);
        return Page.ALL_APARTMENTS;
    }
}
