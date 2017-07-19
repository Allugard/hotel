package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for commands
 */
public interface Command {

    /**
     * executes some command, returns address to JSP page which will be forwarded by servlet to show results
     *
     * @param request  HTTP request from servlet
     * @param response HTTP response from servlet
     * @return name of JSP page which will be forwarded by servlet
     * @throws DaoException if some error in DAO layer
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException;
}
