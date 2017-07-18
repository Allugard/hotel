package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.service.UserService;
import ua.allugard.hotel.util.Messages;
import ua.allugard.hotel.util.Page;
import ua.allugard.hotel.util.Parameters;
import ua.allugard.hotel.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by allugard on 08.07.17.
 */
public class SignInCommand implements Command {

    private UserService userService;

    SignInCommand(UserService userService) {
        this.userService = userService;
    }

    private static class Holder {
        static final SignInCommand INSTANCE = new SignInCommand(UserService.getInstance());
    }

    public static SignInCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);

        List<String> errors = validate(login, password);
        if (!errors.isEmpty()){
            setAttributesToRequest(request, login, errors);
            return Page.LOGIN;
        }
        Optional<User> user = userService.findUserByLoginPassword(login, password);

        if (!user.isPresent()) {
            errors.add(Messages.ERROR_LOGIN_PASS_MESSAGE);
            setAttributesToRequest(request, login, errors);
            return Page.LOGIN;
        }

        request.getSession().setAttribute(Parameters.USER, user.get());
        return Page.PROFILE;
    }

    private void setAttributesToRequest(HttpServletRequest request, String login, List<String> errors){
        request.setAttribute(Parameters.LOGIN, login);
        request.setAttribute(Parameters.ERRORS, errors);
    }

    private List<String> validate(String login, String password) {
        List<String> errors = new ArrayList<>();

        Validator validator = Validator.getInstance();

//        if(!validator.validateLogin(login)){
//            errors.add(Messages.INVALID_LOGIN);
//        }
//        if(!validator.validatePassword(password)){
//            errors.add(Messages.INVALID_PASSWORD);
//        }
        return errors;
    }

}
