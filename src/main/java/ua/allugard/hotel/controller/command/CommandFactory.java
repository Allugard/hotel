package ua.allugard.hotel.controller.command;

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
        commands.put(Path.LOGIN, new GetLoginPageCommand());
        commands.put(Path.AUTHORIZATION, AuthorizationCommand.getInstance());
        commands.put(Path.REGISTRATION, new GetRegistrationPageCommand());
        commands.put(Path.SIGN_UP, SignUpCommand.getInstance());
    }

    private static class Holder {
        static final CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return Holder.INSTANCE;
    }


    public Command getCommand(HttpServletRequest request) {
        String path = request.getServletPath();
        System.out.println(request.getServletPath());
        System.out.println(request.getContextPath());
        Command command = commands.get(path);
//        if (command == null){
//            command = commands.get("default");
//        }
        return command;
    }



}
