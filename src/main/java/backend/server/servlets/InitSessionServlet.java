package backend.server.servlets;

import backend.implementations.SQLite.connector.SQLiteDatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.administration.data.SimulationBillAdministrator;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.administration.centralControl.SimulatorSwitcher;
import backend.model.simulation.settings.settingsData.SettingsBuilder;
import backend.server.sockets.CompanyWebSocket;
import backend.server.sockets.EventWebSocket;
import backend.server.sockets.PersonWebSocket;
import backend.server.sockets.SimulableCounterWebSocket;
import backend.utils.FrontControllerUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import java.io.IOException;

public class InitSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        SimulatorSwitcher.setUriClient(getServletContext().getRealPath("/CSVFiles/Clients.csv"));
        SimulatorSwitcher.setUriProvider(getServletContext().getRealPath("/CSVFiles/Providers.csv"));
        CFDIBillGenerator.setUri(getServletContext().getRealPath("/xmlFiles")+"/");
        SQLiteDatabaseConnector.setUri("jdbc:sqlite:" + getServletContext().getRealPath("/Simulator.db"));
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