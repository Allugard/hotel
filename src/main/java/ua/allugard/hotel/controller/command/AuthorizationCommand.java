package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class AuthorizationCommand implements Command {

    private UserService userService;

    public AuthorizationCommand(UserService userService) {
        this.userService = userService;
    }

    public static AuthorizationCommand getInstance() {
        return new AuthorizationCommand(UserService.getInstance());
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        userService.findUserByLoginPassword(login, password);

        return null;
    }


}
