package backend.server.servlets;

import backend.model.simulation.administration.SessionAdministrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        Integer id = (Integer) request.getSession(true).getAttribute("session");
        if(id == null || id == 0) request.getSession(true).setAttribute("session",addSession());
        try {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private Integer addSession() {
        return SessionAdministrator.getSessionList().addSession();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }
}