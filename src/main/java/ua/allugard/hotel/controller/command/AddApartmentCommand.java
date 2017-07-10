package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.ZoneId;

/**
 * Created by allugard on 08.07.17.
 */
public class AddApartmentCommand implements Command {

    private ApartmentService apartmentService;

    public AddApartmentCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    public static AddApartmentCommand getInstance() {
        return new AddApartmentCommand(ApartmentService.getInstance());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
