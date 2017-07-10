package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.ZoneId;

/**
 * Created by allugard on 08.07.17.
 */
public class DeleteBookingCommand implements Command {

    private BookingService bookingService;

    public DeleteBookingCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public static DeleteBookingCommand getInstance() {
        return new DeleteBookingCommand(BookingService.getInstance());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("delete"));

        bookingService.delete(id);
        return Page.PROFILE;
    }
}
