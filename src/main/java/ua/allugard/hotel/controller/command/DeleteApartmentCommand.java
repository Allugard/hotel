package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class DeleteApartmentCommand implements Command {

    private ApartmentService apartmentService;

    public DeleteApartmentCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    public static DeleteApartmentCommand getInstance() {
        return new DeleteApartmentCommand(ApartmentService.getInstance());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("delete"));
        apartmentService.delete(id);
        return Page.ALL_APARTMENTS;
    }
}
