package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Bill;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.BillService;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.constants.Page;
import ua.allugard.hotel.util.constants.Parameters;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 08.07.17.
 */
public class BookingsByUserCommand implements Command {

    private BookingService bookingService;
    private BillService billService;

    BookingsByUserCommand(BookingService bookingService, BillService billService) {
        this.bookingService = bookingService;
        this.billService = billService;
    }

    private static class Holder {
        static final BookingsByUserCommand INSTANCE = new BookingsByUserCommand(BookingService.getInstance(), BillService.getInstance());
    }

    public static BookingsByUserCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        List<Booking> bookings = createBookings(request);

        request.setAttribute(Parameters.BOOKINGS, bookings);
        return Page.BOOKINGS_BY_USER;
    }

    private List<Booking> createBookings(HttpServletRequest request) throws DaoException {
        List<Booking> bookings = bookingService.findByUser(((User) request.getSession().getAttribute(Parameters.USER)).getUserAuthentication().getId());

        for (Booking booking: bookings) {
            if(booking.getStatus().equals(Booking.Status.CONFIRMED)){
                Optional<Bill> bill = billService.findByBooking(booking);
                if(bill.isPresent()) {
                    booking.setBill(bill.get());
                }
            }
        }

        return bookings;
    }
}
