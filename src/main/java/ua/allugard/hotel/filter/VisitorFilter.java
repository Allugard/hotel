package ua.allugard.hotel.filter;

import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.util.constants.Parameters;
import ua.allugard.hotel.util.constants.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by allugard on 19.07.17.
 */
public abstract class VisitorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        User user = null;

        if(session != null) {
            user = ((User) req.getSession().getAttribute(Parameters.USER));
        }

        if(isNotAllowed(req, user)) {
            resp.sendRedirect(Path.MAIN);
            return;
        }

        chain.doFilter(request, response);
    }

    abstract boolean isNotAllowed(HttpServletRequest req, User user);

}
