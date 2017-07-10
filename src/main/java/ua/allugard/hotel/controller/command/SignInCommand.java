package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.UserService;
import ua.allugard.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by allugard on 08.07.17.
 */
public class SignInCommand implements Command {

    private UserService userService;

    public SignInCommand(UserService userService) {
        this.userService = userService;
    }

    public static SignInCommand getInstance() {
        return new SignInCommand(UserService.getInstance());
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<User> user = userService.findUserByLoginPassword(login, password);
        if (user.isPresent()) {
            request.getSession().setAttribute("user", user.get());
        }
        return Page.PROFILE;
    }


}