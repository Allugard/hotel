package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.Commands;
import ua.allugard.hotel.util.Path;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by allugard on 06.07.17.
 */
public class CommandFactory {
    Map<String, Command> commands;

    CommandFactory() {
        commands = new HashMap<>();
//        commands.put(Path.LOGIN, new GetLoginPageCommand());
        commands.put(Commands.REDIRECT, new RedirectCommand());

//        commands.put(Path.LOGOUT, new LogoutCommand());
//        commands.put(Path.PROFILE, new GetProfilePageCommand());
//        commands.put(Path.AUTHORIZATION, AuthorizationCommand.getInstance());
//        commands.put(Path.REGISTRATION, new GetRegistrationPageCommand());
//        commands.put(Path.SIGN_UP, SignUpCommand.getInstance());
//        commands.put(Path.VIEW_BOOKINGS, GetBookingsByClientCommand.getInstance());
//        commands.put(Path.ADD_BOOKING_PAGE, GetAddBookingPageCommand.getInstance());
//        commands.put(Path.ADD_BOOKING, AddBookingCommand.getInstance());
//        commands.put(Path.DELETE_BOOKING, DeleteBookingCommand.getInstance());
//        commands.put(Path.ADD_APARTMENT_PAGE, GetAddApartmentPageCommand.getInstance());
//        commands.put(Path.ADD_APARTMENT, AddApartmentCommand.getInstance());
//        commands.put(Path.VIEW_ALL_APARTMENTS, GetAllApartmentsCommand.getInstance());
//        commands.put(Path.DELETE_APARTMENT, DeleteApartmentCommand.getInstance());
    }

    private static class Holder {
        static final CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return Holder.INSTANCE;
    }


    public Command getCommand(HttpServletRequest request) {
        String path = request.getServletPath();
        String action = request.getParameter("command");
        System.out.println("ACTION" + action);
//        System.out.println(request.getAttribute("command"));
        Command command = commands.get(action);
//        if (command == null){
//            command = commands.get("default");
//        }
//        System.out.println(command.getClass());
        return command;
    }



}
