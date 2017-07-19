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
public class ClientFilter extends VisitorFilter {

    private List<String> notAllowedForClient;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        notAllowedForClient = new ArrayList<>();
        notAllowedForClient.add(Path.LOGIN);
        notAllowedForClient.add(Path.REGISTRATION);
        notAllowedForClient.add(Path.ADD_APARTMENT_PAGE);
        notAllowedForClient.add(Path.PROCESSED_BOOKINGS);
        notAllowedForClient.add(Path.ALL_APARTMENTS);
    }



    boolean isNotAllowed(HttpServletRequest req, User user) {
        return user != null && user.getUserAuthentication().getRole().equals(UserAuthentication.Role.CLIENT) && notAllowedForClient.contains(req.getServletPath());
    }

    @Override
    public void destroy() {

    }
}
