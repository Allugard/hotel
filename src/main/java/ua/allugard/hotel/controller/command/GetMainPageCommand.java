package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 07.07.17.
 */
public class GetMainPageCommand implements Command {

    private static class Holder {
        static final GetMainPageCommand INSTANCE = new GetMainPageCommand();
    }

    public static GetMainPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.MAIN;
    }
}
