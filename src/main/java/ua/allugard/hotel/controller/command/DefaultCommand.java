package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //TODO: set error
        return Path.MAIN;
    }
}
