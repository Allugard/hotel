package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.constants.Page;
import ua.allugard.hotel.util.constants.Parameters;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by allugard on 08.07.17.
 */
public class ProcessedBookingsCommand implements Command {

    private BookingService bookingService;
    private ApartmentService apartmentService;
    private static final int RECORDS_PER_PAGE = 5;


    ProcessedBookingsCommand(BookingService bookingService, ApartmentService apartmentService) {
        this.bookingService = bookingService;
        this.apartmentService = apartmentService;
    }

    private static class Holder {
        static final ProcessedBookingsCommand INSTANCE = new ProcessedBookingsCommand(BookingService.getInstance(), ApartmentService.getInstance());
    }

    public static ProcessedBookingsCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        int page = getPage(request);

        List<Booking> bookings = bookingService.findProcessedBookings((page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
        int numberOfPages = getNumberOfPages();
        List<List<Apartment>> freeNumbersForBooking = getFreeNumbers(bookings);

        setAttributesToRequest(request, bookings, freeNumbersForBooking, numberOfPages, page);

        return Page.PROCESSED_BOOKINGS;
    }

    private void setAttributesToRequest(HttpServletRequest request, List<Booking> bookings, List<List<Apartment>> freeNumbersForBooking, int numberOfPages, int page) {
        request.setAttribute(Parameters.BOOKINGS, bookings);
        request.setAttribute(Parameters.FREE_NUMBERS, freeNumbersForBooking);
        request.setAttribute(Parameters.NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(Parameters.CURRENT_PAGE, page);
    }

    private List<List<Apartment>> getFreeNumbers(List<Booking> bookings) throws DaoException {
        List<List<Apartment>> freeNumbersForBooking = new ArrayList<>();
        for (Booking booking: bookings) {
            freeNumbersForBooking.add(apartmentService.findFreeApartments(booking));
        }
        return freeNumbersForBooking;
    }

    private int getNumberOfPages() throws DaoException {
        int numberOfRecords = bookingService.getNumberOfPagesForProcessedBookings();
        int numberOfPages = (int) Math.ceil((double)numberOfRecords / RECORDS_PER_PAGE);
        return numberOfPages;
    }

    private int getPage(HttpServletRequest request) {
        int page;
        if(request.getParameter(Parameters.PAGE) != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }else {
            page = 1;
        }
        return page;
    }
}
