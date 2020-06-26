package backend.server.servlets;

import backend.implementations.SQLite.connector.DatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.administration.data.SimulationBillAdministrator;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.administration.centralControl.SimulatorSwitcher;
import backend.model.simulation.settings.settingsData.SettingsBuilder;
import backend.utils.FrontControllerUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitSimulationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        initUris();
        SimulationBillAdministrator.resetBills();
        SimulationDataController.initSimulationData();
        SettingsBuilder.setCurrentSettingsToSession(request);
        FrontControllerUtils.setQuickSettings(request);
        forwardToMainPage(request, response);
    }

    private void initUris() {
        SimulatorSwitcher.setUriClient(getServletContext().getRealPath("/CSVFiles/Clients.csv"));
        SimulatorSwitcher.setUriProvider(getServletContext().getRealPath("/CSVFiles/Providers.csv"));
        CFDIBillGenerator.setUri(getServletContext().getRealPath("/xmlFiles")+"/");
        DatabaseConnector.setUri("jdbc:sqlite:" + getServletContext().getRealPath("/Simulator.db"));
    }

    private void forwardToMainPage(HttpServletRequest request, HttpServletResponse response) {
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