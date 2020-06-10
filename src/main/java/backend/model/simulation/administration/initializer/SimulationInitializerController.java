package backend.model.simulation.administration.initializer;

import backend.model.event.EventController;
import backend.model.simulables.Simulable;
import backend.model.simulation.administration.centralControl.SimulationBillAdministrator;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;
import backend.model.simulation.settings.settingsList.GeneralSettings;

import java.sql.SQLException;
import java.util.List;

public class SimulationInitializerController {
    public static List<Simulable> init(){
        initSimulables();
        SimulationFollowAdministrator.followRandomOptions();
        return SimulationInitializer.init();
    }

    private static void initSimulables(){
        try {
            int serviceCount = GeneralSettings.getServiceCount();
            SimulationDataController.getSimulationData().getCompanyList().addAll(SimulationInitializer.getServiceCompanies(serviceCount));
            int providerCount = GeneralSettings.getProviderCount();
            SimulationDataController.getSimulationData().getCompanyList().addAll(SimulationInitializer.getProviders(providerCount));
            int restaurantCount = GeneralSettings.getRestaurantCount();
            SimulationDataController.getSimulationData().getCompanyList().addAll(SimulationInitializer.getRestaurants(restaurantCount));
            int clientCount = GeneralSettings.getClientCount();
            SimulationDataController.getSimulationData().getClientList().addAll(SimulationInitializer.getClients(clientCount));
            int workerCount = GeneralSettings.getWorkerCount();
            SimulationDataController.getSimulationData().getClientList().addAll(SimulationInitializer.getWorkers(workerCount));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void reset(){
        SimulationDataController.getSimulationData().reset();
        SimulationBillAdministrator.resetBills();
        resetEvents();
    }

    private static void resetEvents() {
        EventController.resetEvents();
    }
}
