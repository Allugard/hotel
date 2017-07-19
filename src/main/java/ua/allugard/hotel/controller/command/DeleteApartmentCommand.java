package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.util.constants.Page;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class DeleteApartmentCommand implements Command {

    private ApartmentService apartmentService;

    DeleteApartmentCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    private static class Holder {
        static final DeleteApartmentCommand INSTANCE = new DeleteApartmentCommand(ApartmentService.getInstance());
    }

    public static DeleteApartmentCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        int id = Integer.parseInt(request.getParameter("delete"));
        apartmentService.delete(id);
        return Page.ALL_APARTMENTS;
    }
}
