package backend.server.servlets;

import backend.model.simulation.administration.centralControl.SimulationBillAdministrator;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.server.EJB.dataSettings.SettingsBuilder;
import backend.utils.FrontControllerUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        SimulationBillAdministrator.resetBills();
        SimulationDataController.initSessionData();
        SettingsBuilder.setCurrentSettingsToSession(request);
        FrontControllerUtils.setQuickSettings(request);
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