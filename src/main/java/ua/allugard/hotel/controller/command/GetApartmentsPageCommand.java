package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.constants.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 07.07.17.
 */
public class GetApartmentsPageCommand implements Command {

    private static class Holder {
        static final GetApartmentsPageCommand INSTANCE = new GetApartmentsPageCommand();
    }

    public static GetApartmentsPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.APARTMENTS;
    }
}
