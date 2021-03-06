package backend.model.simulation.administration.initializer;

import backend.model.event.EventController;
import backend.model.simulables.Simulable;
import backend.model.simulation.administration.centralControl.SimulatorSwitcher;
import backend.model.simulation.administration.data.SimulationBillAdministrator;
import backend.model.simulation.administration.data.SimulationDataAdministrator;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;
import backend.model.simulation.settings.settingsList.GeneralSettings;
import backend.utils.SimulationFileWriter;

import java.sql.SQLException;
import java.util.List;

public class SimulationInitializerController {
    public static List<Simulable> initExecution(){
        initSimulables();
        SimulationFollowAdministrator.followRandomOptions();
        return SimulationInitializer.init();
    }

    private static void initSimulables(){
        try {
            int serviceCount = GeneralSettings.getServiceCount();
            SimulationDataAdministrator.getSimulationData().getCompanyList().addAll(SimulationInitializer.getServiceCompanies(serviceCount));
            int providerCount = GeneralSettings.getProviderCount();
            SimulationDataAdministrator.getSimulationData().getCompanyList().addAll(SimulationInitializer.getProviders(providerCount));
            int restaurantCount = GeneralSettings.getRestaurantCount();
            SimulationDataAdministrator.getSimulationData().getCompanyList().addAll(SimulationInitializer.getRestaurants(restaurantCount));
            int clientCount = GeneralSettings.getClientCount();
            SimulationDataAdministrator.getSimulationData().getClientList().addAll(SimulationInitializer.getClients(clientCount));
            int workerCount = GeneralSettings.getWorkerCount();
            SimulationDataAdministrator.getSimulationData().getClientList().addAll(SimulationInitializer.getWorkers(workerCount));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void reset(){
        SimulationFileWriter.insert("", SimulatorSwitcher.getUriEvents());
        EventController.resetEvents();
        SimulationDataAdministrator.getSimulationData().reset();
        SimulationBillAdministrator.resetBills();
    }

    public static void init(){
        SimulationDataAdministrator.initSimulationData();
        reset();
    }
}
