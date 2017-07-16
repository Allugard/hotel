package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.dao.util.ConnectionManager;
import ua.allugard.hotel.model.dao.util.DaoFactory;
import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.model.service.BillService;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.ZoneId;

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
        apartmentService.create(apartment);
        return Page.ADD_APARTMENT;
    }

    private Apartment createApartmentFromRequest(HttpServletRequest request) {
        return new Apartment.Builder()
                .setPrice(Integer.parseInt(request.getParameter("price")))
                .setCapacity(Integer.parseInt(request.getParameter("capacity")))
                .setApartmentsType(Apartment.ApartmentsType.valueOf(request.getParameter("apartmentsType").toUpperCase()))
                .setNumber(request.getParameter("number"))
                .build();
    }

}
