package backend.model.simulation.administration.data;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.model.simulation.administration.simulableControl.SimulableController;
import backend.model.simulation.administration.simulableControl.SimulationCommitter;
import backend.model.simulation.administration.simulableControl.SimulationIOController;
import backend.model.simulation.timeLine.TimeLine;
import backend.model.simulation.settings.settingsData.sessionData.*;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationData {

    private List<Company> companyList;
    private List<Client> clientList;
    private List<Simulable> followedSimulables;
    private SimulableController simulableController;

    private GeneralDataSettings generalDataSettings;
    private RestaurantDataSettings restaurantDataSettings;
    private ClientDataSettings clientDataSettings;
    private ProviderDataSettings providerDataSettings;
    private ServiceSessionData serviceSessionData;
    private WorkerSessionData workerSessionData;

    private AtomicBoolean executing;
    private AtomicBoolean restart;
    private TimeLine timeLine;

    public SimulationData() {
        reset();
    }


    public List<Company> getCompanyList() {
        return companyList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Simulable> getFollowedSimulables() {
        return followedSimulables;
    }

    public AtomicBoolean getExecuting() {
        return executing;
    }

    public AtomicBoolean getRestart() {
        return restart;
    }

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public SimulableController getSimulableController() {
        return simulableController;
    }

    public void initTimeLine(List<Simulable> simulableList){
        timeLine = new TimeLine(simulableList);
        simulableController.initIO(new SimulationIOController(timeLine.getSimulableList(),new SimulationCommitter()));
        restart.set(false);
    }

    public void reset() {
        initSimulables();
        initSimulator();
        initSettings();
    }

    public void initSimulator() {
        simulableController = new SimulableController(new SimulationCommitter());
        restart = new AtomicBoolean(true);
        executing = new AtomicBoolean(true);
    }

    public void initSimulables() {
        companyList = new CopyOnWriteArrayList<>();
        clientList = new CopyOnWriteArrayList<>();
        followedSimulables = new LinkedList<>();
    }

    private void initSettings() {
        generalDataSettings = new GeneralDataSettings();
        clientDataSettings = new ClientDataSettings();
        restaurantDataSettings = new RestaurantDataSettings();
        providerDataSettings = new ProviderDataSettings();
        serviceSessionData = new ServiceSessionData();
        workerSessionData = new WorkerSessionData();
    }

    public GeneralDataSettings getGeneralDataSettings() {
        return generalDataSettings;
    }

    public ClientDataSettings getClientDataSettings() {
        return clientDataSettings;
    }

    public RestaurantDataSettings getRestaurantDataSettings() {
        return restaurantDataSettings;
    }

    public ProviderDataSettings getProviderDataSettings() {
        return providerDataSettings;
    }

    public ServiceSessionData getServiceSessionData() {
        return serviceSessionData;
    }

    public WorkerSessionData getWorkerSessionData() {
        return workerSessionData;
    }
}
