package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class DeleteBookingCommand implements Command {

    private BookingService bookingService;

    DeleteBookingCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private static class Holder {
        static final DeleteBookingCommand INSTANCE = new DeleteBookingCommand(BookingService.getInstance());
    }

    public static DeleteBookingCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        int id = Integer.parseInt(request.getParameter("delete"));

        bookingService.delete(id);
        return BookingsByUserCommand.getInstance().execute(request, response);
    }
}
