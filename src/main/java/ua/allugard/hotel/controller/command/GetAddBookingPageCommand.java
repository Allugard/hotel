package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.constants.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class GetAddBookingPageCommand implements Command {

    private static class Holder {
        static final GetAddBookingPageCommand INSTANCE = new GetAddBookingPageCommand();
    }

    public static GetAddBookingPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.ADD_BOOKING;
    }
}
