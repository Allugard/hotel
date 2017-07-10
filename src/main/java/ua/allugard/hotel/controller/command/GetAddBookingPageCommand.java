package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class GetAddBookingPageCommand implements Command {

    public static GetAddBookingPageCommand getInstance() {
        return new GetAddBookingPageCommand();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.ADD_BOOKING;
    }
}
