package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by allugard on 08.07.17.
 */
public class GetBookingsByClientCommand implements Command {

    private BookingService bookingService;

    public GetBookingsByClientCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public static GetBookingsByClientCommand getInstance() {
        return new GetBookingsByClientCommand(BookingService.getInstance());
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Booking> bookings = null;
        if (request.getSession().getAttribute("user") instanceof User) {
             bookings = bookingService.findByUser(((User) request.getSession().getAttribute("user")).getUserAuthentication().getId());
        }
            request.setAttribute("bookings", bookings);
        return Page.BOOKINGS_BY_CLIENT;
    }
}