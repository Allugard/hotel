package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.model.entity.User;
import ua.allugard.hotel.model.entity.UserAuthentication;
import ua.allugard.hotel.model.service.UserService;
import ua.allugard.hotel.util.Hasher;
import ua.allugard.hotel.util.constants.Messages;
import ua.allugard.hotel.util.constants.Page;
import ua.allugard.hotel.util.constants.Parameters;
import ua.allugard.hotel.util.Validator;
import ua.allugard.hotel.util.exceptions.DaoException;
import ua.allugard.hotel.util.exceptions.DuplicateLoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        User user = createUserFromRequest(request);

        List<String> errors = validateUser(user);

        if(!errors.isEmpty()){
            setAttributesToRequest(request, user, errors);
            return Page.REGISTRATION;
        }

        boolean created = createUser(user, errors);



        if(!created){
            setAttributesToRequest(request, user, errors);
            return Page.REGISTRATION;
        }

        setSuccessAtributeToRequest(request);
        return Page.MAIN;
    }

    private boolean createUser(User user, List<String> errors) throws DaoException {
        boolean created = false;
        try {
            created = userService.create(user);
        } catch (DuplicateLoginException e) {
            errors.add(Messages.DUPLICATE_LOGIN);
        }
        return created;
    }

    private void setSuccessAtributeToRequest(HttpServletRequest request) {
        request.setAttribute(Parameters.COMPLETED_REGISTRATION, Messages.COMPLETED_REGISTRATION);
    }

    private void setAttributesToRequest(HttpServletRequest request, User user, List<String> errors){
        request.setAttribute(Parameters.LOGIN, user.getUserAuthentication().getLogin());
        request.setAttribute(Parameters.FIRST_NAME, user.getFirstName());
        request.setAttribute(Parameters.LAST_NAME, user.getLastName());
        request.setAttribute(Parameters.PHONE, user.getPhone());
        request.setAttribute(Parameters.ERRORS, errors);
    }

    private List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        Validator validator = Validator.getInstance();

        if(!validator.validateLogin(user.getUserAuthentication().getLogin())){
            errors.add(Messages.INVALID_LOGIN);
        }
        if(!validator.validatePassword(user.getUserAuthentication().getPassword())){
            errors.add(Messages.INVALID_PASSWORD);
        }

        if(!validator.validateName(user.getFirstName())){
            errors.add(Messages.INVALID_FIRST_NAME);
        }

        if(!validator.validateName(user.getLastName())){
            errors.add(Messages.INVALID_LAST_NAME);
        }

        if(!validator.validatePhone(user.getPhone())){
            errors.add(Messages.INVALID_PHONE);
        }

        return errors;

    }

    private User createUserFromRequest(HttpServletRequest request) {
        return new User.Builder()
                .setPhone(request.getParameter(Parameters.PHONE))
                .setLastName(request.getParameter(Parameters.LAST_NAME))
                .setFirstName(request.getParameter(Parameters.FIRST_NAME))
                .setUserAuthentication(new UserAuthentication.Builder()
                        .setLogin(request.getParameter(Parameters.LOGIN))
                        .setPassword(Hasher.hashCode(request.getParameter(Parameters.PASSWORD)))
                        .setRole(UserAuthentication.Role.CLIENT)
                        .build())
                .build();
    }

}
