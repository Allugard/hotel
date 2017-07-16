package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by allugard on 08.07.17.
 */
public class BookingsByUserCommand implements Command {

    private BookingService bookingService;

    BookingsByUserCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private static class Holder {
        static final BookingsByUserCommand INSTANCE = new BookingsByUserCommand(BookingService.getInstance());
    }

    public static BookingsByUserCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Booking> bookings = null;
        if (request.getSession().getAttribute("user") instanceof User) {
             bookings = bookingService.findByUser(((User) request.getSession().getAttribute("user")).getUserAuthentication().getId());
        }
            request.setAttribute("bookings", bookings);
        return Page.BOOKINGS_BY_USER;
    }
}
