package backend.server.servlets;

import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.timeLine.Speed;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.EJB.dataSettings.SettingsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        SimulationDataController.initSessionData();
        SettingsBuilder.setCurrentSettingsToSession(request);
        request.getSession(true).setAttribute(Speed.class.getSimpleName(), TimeLine.getSpeed());
        try {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }
}