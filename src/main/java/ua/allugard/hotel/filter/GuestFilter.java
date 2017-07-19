package ua.allugard.hotel.filter;

import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.entity.UserAuthentication;
import ua.allugard.hotel.util.constants.Parameters;
import ua.allugard.hotel.util.constants.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by allugard on 16.07.17.
 */
public class GuestFilter extends VisitorFilter {

    private List<String> notAllowedForGuest;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        notAllowedForGuest = new ArrayList<>();
        notAllowedForGuest.add(Path.LOGOUT);
        notAllowedForGuest.add(Path.PROFILE);
        notAllowedForGuest.add(Path.ADD_BOOKING_PAGE);
        notAllowedForGuest.add(Path.BOOKINGS_BY_USER);
        notAllowedForGuest.add(Path.ADD_APARTMENT_PAGE);
        notAllowedForGuest.add(Path.PROCESSED_BOOKINGS);
        notAllowedForGuest.add(Path.ALL_APARTMENTS);
    }

    boolean isNotAllowed(HttpServletRequest req, User user) {
        return user == null && notAllowedForGuest.contains(req.getServletPath());
    }

    @Override
    public void destroy() {

    }
}
