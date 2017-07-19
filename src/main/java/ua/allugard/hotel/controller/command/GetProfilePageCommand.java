package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.constants.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 07.07.17.
 */
public class GetProfilePageCommand implements Command {

    private static class Holder {
        static final GetProfilePageCommand INSTANCE = new GetProfilePageCommand();
    }

    public static GetProfilePageCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.PROFILE;
    }
}
