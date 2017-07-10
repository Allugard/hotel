package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 10.07.17.
 */
public class RedirectCommand implements Command {

    public static RedirectCommand getInstance() {
        return new RedirectCommand();
    }

        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        System.out.println(page);
        return page;
    }
}
