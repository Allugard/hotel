package ua.allugard.hotel.controller;

import org.apache.log4j.Logger;
import ua.allugard.hotel.controller.command.Command;
import ua.allugard.hotel.controller.command.CommandFactory;
import ua.allugard.hotel.model.dao.impl.JdbcApartmentDao;
import ua.allugard.hotel.util.constants.LogMessage;
import ua.allugard.hotel.util.constants.Messages;
import ua.allugard.hotel.util.constants.Page;
import ua.allugard.hotel.util.Resolver;
import ua.allugard.hotel.util.exceptions.DaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by allugard on 05.07.17.
 */
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(FrontController.class);
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
        String path = Resolver.resolve(page);
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
            e.printStackTrace();
            LOGGER.error(LogMessage.ERROR_IN_DAO_LAYER + e);
        } catch (Exception e){
            page = Page.ERROR;
            e.printStackTrace();
            LOGGER.error(e);
        }

        return page;
    }


}

