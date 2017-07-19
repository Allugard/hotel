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
public class AdminFilter extends VisitorFilter {

    private List<String> notAllowedForAdmin;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        notAllowedForAdmin = new ArrayList<>();
        notAllowedForAdmin.add(Path.LOGIN);
        notAllowedForAdmin.add(Path.REGISTRATION);
        notAllowedForAdmin.add(Path.ADD_BOOKING_PAGE);
        notAllowedForAdmin.add(Path.BOOKINGS_BY_USER);
    }

    boolean isNotAllowed(HttpServletRequest req, User user) {
        return user != null && user.getUserAuthentication().getRole().equals(UserAuthentication.Role.ADMIN) && notAllowedForAdmin.contains(req.getServletPath());
    }

    @Override
    public void destroy() {

    }
}
