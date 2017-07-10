package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by allugard on 10.07.17.
 */
public class GetAllApartmentsCommand implements Command{
    private ApartmentService apartmentService;

    public GetAllApartmentsCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    public static GetAllApartmentsCommand getInstance() {
        return new GetAllApartmentsCommand(ApartmentService.getInstance());
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Apartment> apartments;
        apartments = apartmentService.findAll();
        request.setAttribute("apartments", apartments);
        return Page.ALL_APARTMENTS;
    }
}
