package ua.allugard.hotel.controller;

import ua.allugard.hotel.controller.command.Command;
import ua.allugard.hotel.controller.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by allugard on 05.07.17.
 */
public class FrontController extends HttpServlet {
    private CommandFactory commandFactory;

    public FrontController() {
        System.out.println("FRONTCONTROLLER GOOD");
        commandFactory = CommandFactory.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST");

        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET");

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = commandFactory.getCommand(request);
        String page = command.execute(request, response);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/"+page+".jsp");
        dispatcher.forward(request, response);
    }


}

