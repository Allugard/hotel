package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 06.07.17.
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException;
}
