package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.Apartment;
import ua.allugard.hotel.model.entity.Booking;
import ua.allugard.hotel.model.service.ApartmentService;
import ua.allugard.hotel.model.service.BookingService;
import ua.allugard.hotel.util.Page;
import ua.allugard.hotel.util.Parameters;

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
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = getPage(request);

        List<Booking> bookings = bookingService.findProcessedBookings((page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
        int numberOfPages = getNumberOfPages();
        List<List<Apartment>> freeNumbersForBooking = getFreeNumbers(bookings);

//        for (List<Apartment> apartments: freeNumbersForBooking) {
//            System.out.println(apartments);
//        }

        request.setAttribute("bookings", bookings);
        request.setAttribute("freeNumbersForBooking", freeNumbersForBooking);
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", page);

        return Page.PROCESSED_BOOKINGS;
    }

    private List<List<Apartment>> getFreeNumbers(List<Booking> bookings) {
        List<List<Apartment>> freeNumbersForBooking = new ArrayList<>();
        for (Booking booking: bookings) {
//            System.out.println("        " + apartmentService.findFreeApartments(booking));
            freeNumbersForBooking.add(apartmentService.findFreeApartments(booking));
        }
        return freeNumbersForBooking;
    }

    private int getNumberOfPages() {
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
