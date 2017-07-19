package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.constants.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class GetAddApartmentPageCommand implements Command {

    private static class Holder {
        static final GetAddApartmentPageCommand INSTANCE = new GetAddApartmentPageCommand();
    }

    public static GetAddApartmentPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.ADD_APARTMENT;
    }
}
