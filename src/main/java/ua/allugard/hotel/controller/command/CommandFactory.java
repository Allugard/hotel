package ua.allugard.hotel.controller.command;

import ua.allugard.hotel.util.constants.Path;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for commands.
 */
public class CommandFactory {
    Map<String, Command> commands;

    CommandFactory() {
        commands = new HashMap<>();

        commands.put(Path.LOGOUT, LogoutCommand.getInstance());
        commands.put(Path.SIGN_UP, SignUpCommand.getInstance());
        commands.put(Path.SIGN_IN, SignInCommand.getInstance());
        commands.put(Path.LOGIN, GetLoginPageCommand.getInstance());
        commands.put(Path.MAIN, GetMainPageCommand.getInstance());
        commands.put(Path.ADD_BOOKING_PAGE, GetAddBookingPageCommand.getInstance());
        commands.put(Path.BOOKINGS_BY_USER, BookingsByUserCommand.getInstance());
        commands.put(Path.ADD_BOOKING, AddBookingCommand.getInstance());
        commands.put(Path.ALL_APARTMENTS, AllApartmentsCommand.getInstance());
        commands.put(Path.ADD_APARTMENT_PAGE, GetAddApartmentPageCommand.getInstance());
        commands.put(Path.ADD_APARTMENT, AddApartmentCommand.getInstance());
        commands.put(Path.DELETE_APARTMENT, DeleteApartmentCommand.getInstance());
        commands.put(Path.DELETE_BOOKING, DeleteBookingCommand.getInstance());
        commands.put(Path.PROFILE, GetProfilePageCommand.getInstance());
        commands.put(Path.PROCESSED_BOOKINGS, ProcessedBookingsCommand.getInstance());
        commands.put(Path.UPDATE_BOOKING, UpdateBookingCommand.getInstance());
        commands.put(Path.REGISTRATION, GetRegistrationPageCommand.getInstance());



    }

    private static class Holder {
        static final CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return Holder.INSTANCE;
    }


    public Command getCommand(HttpServletRequest request) {
        String path = request.getServletPath();

        Command command = commands.get(path);

        if(command == null){
            command = GetMainPageCommand.getInstance();
        }
        return command;
    }



}
