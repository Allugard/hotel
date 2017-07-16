package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.entity.UserAuthentication;
import ua.allugard.hotel.model.service.UserService;
import ua.allugard.hotel.util.Page;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by allugard on 08.07.17.
 */
public class SignUpCommand implements Command {

    private UserService userService;

    public SignUpCommand(UserService userService) {
        this.userService = userService;
    }

    private static class Holder {
        static final SignUpCommand INSTANCE = new SignUpCommand(UserService.getInstance());
    }

    public static SignUpCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = createUserFromRequest(request);
//        System.out.println(user);
        try {
            boolean st = userService.create(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
//        System.out.println("SUC   " + st);
        return Page.MAIN;
    }

    private User createUserFromRequest(HttpServletRequest request) {
        return new User.Builder()
                .setPhone(request.getParameter("phone"))
                .setLastName(request.getParameter("lastName"))
                .setFirstName(request.getParameter("firstName"))
                .setUserAuthentication(new UserAuthentication.Builder()
                        .setLogin(request.getParameter("login"))
                        .setPassword(request.getParameter("password"))
                        .setRole(UserAuthentication.Role.CLIENT)
                        .build())
                .build();
    }

}
