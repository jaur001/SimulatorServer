package backend.model.simulation.administration.simulablesControl;

import backend.model.event.EventController;
import backend.model.simulables.Simulable;
import backend.model.simulation.administration.Initializer;
import backend.model.simulation.administration.SimulationBillAdministrator;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;
import backend.model.simulation.settings.settingsList.GeneralSettings;

import java.sql.SQLException;
import java.util.List;

public class SimulationInitializer {
    public static List<Simulable> init(){
        SimulationBillAdministrator.initBillData();
        initSimulables();
        SimulationFollowAdministrator.followRandomOptions();
        return Initializer.init();
    }

    private static void initSimulables(){
        try {
            int serviceCount = GeneralSettings.getServiceCount();
            SimulationDataController.getSessionData().getCompanyList().addAll(Initializer.getServiceCompanies(serviceCount));
            int providerCount = GeneralSettings.getProviderCount();
            SimulationDataController.getSessionData().getCompanyList().addAll(Initializer.getProviders(providerCount));
            int restaurantCount = GeneralSettings.getRestaurantCount();
            SimulationDataController.getSessionData().getCompanyList().addAll(Initializer.getRestaurants(restaurantCount));
            int clientCount = GeneralSettings.getClientCount();
            SimulationDataController.getSessionData().getClientList().addAll(Initializer.getClients(clientCount));
            int workerCount = GeneralSettings.getWorkerCount();
            SimulationDataController.getSessionData().getWorkerList().addAll(Initializer.getWorkers(workerCount));
            SimulationDataController.getSessionData().getClientList().addAll(SimulationDataController.getSessionData().getWorkerList());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void reset(){
        SimulationDataController.getSessionData().reset();
        SimulationBillAdministrator.resetBills();
        resetEvents();
    }

    private static void resetEvents() {
        EventController.resetEvents();
    }
}
