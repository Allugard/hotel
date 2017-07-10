package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class GetAddApartmentPageCommand implements Command {

    public static GetAddApartmentPageCommand getInstance() {
        return new GetAddApartmentPageCommand();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.ADD_APARTMENT;
    }
}
