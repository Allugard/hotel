package ua.allugard.hotel.controller;

import ua.allugard.hotel.controller.command.Command;
import ua.allugard.hotel.controller.command.CommandFactory;
import ua.allugard.hotel.util.Page;
import ua.allugard.hotel.util.exceptions.DaoException;
import ua.allugard.hotel.util.exceptions.DuplicateLoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by allugard on 05.07.17.
 */
public class FrontController extends HttpServlet {

    private CommandFactory commandFactory;

    public FrontController() {
        commandFactory = CommandFactory.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = executeCommand(request, response);
        String path = "/view/"+page+".jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        Command command = commandFactory.getCommand(request);

        String page = null;
        try {
            page = command.execute(request, response);
        } catch (DaoException e) {
            page = Page.ERROR;
        } catch (Exception e){
            e.printStackTrace();
        }

        return page;
    }


}

