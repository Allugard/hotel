package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 10.07.17.
 */
public class LogoutCommand implements Command {

    private static class Holder {
        static final LogoutCommand INSTANCE = new LogoutCommand();
    }

    public static LogoutCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return Page.MAIN;
    }
}
