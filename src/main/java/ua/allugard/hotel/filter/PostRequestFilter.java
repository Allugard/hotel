package ua.allugard.hotel.filter;

import ua.allugard.hotel.util.constants.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by allugard on 16.07.17.
 */
public class PostRequestFilter implements Filter {

    private List<String> postRequests;
    private static final String GET = "GET";
    private static final char SLASH = '/';
    private static final int BEGIN = 0;
    private static final String QUESTION_MARK = "?";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        postRequests = new ArrayList<>();
        postRequests.add(Path.SIGN_IN);
        postRequests.add(Path.SIGN_UP);
        postRequests.add(Path.ADD_APARTMENT);
        postRequests.add(Path.ADD_BOOKING);
        postRequests.add(Path.DELETE_APARTMENT);
        postRequests.add(Path.DELETE_BOOKING);
        postRequests.add(Path.UPDATE_BOOKING);
        postRequests.add(Path.FIND);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String method = req.getMethod();
        String path = req.getServletPath();
        String query = req.getQueryString();

        if(!isAllowed(method, path)){
            path = getNewPath(path, query);
            resp.sendRedirect(path);
            return;
        }
        chain.doFilter(request, response);
    }

    private String getNewPath(String path, String query) {
        path = path.substring(BEGIN, path.lastIndexOf(SLASH));

        if(query != null){
            path.concat(QUESTION_MARK).concat(query);
        }
        return path;
    }

    private boolean isAllowed(String method, String path) {
        return !(method.equalsIgnoreCase(GET) && postRequests.contains(path));
    }

    @Override
    public void destroy() {

    }
}
