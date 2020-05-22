package backend.server.EJB;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.SimulableAdministrator;
import backend.model.simulation.administration.SimulationIOController;
import backend.model.simulation.administration.SimulationCommitter;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.EJB.dataSettings.sessionData.*;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Stateful(name = "SessionDataStatefulEJB")
public class SessionDataStatefulBean {

    private List<Company> companyList;
    private List<Client> clientList;
    private List<Worker> workerList;
    private List<Simulable> followedSimulables;
    private SimulableAdministrator simulableAdministrator;
    private SimulationIOController simulationIOController;

    private GeneralSettingsStatefulBean generalDataSettings;
    private RestaurantSettingsStatefulBean restaurantDataSettings;
    private ClientSettingsStatefulBean clientDataSettings;
    private ProviderSettingsStatefulBean providerDataSettings;
    private BillSettingsStatefulBean billDataSettings;
    private ServiceSessionData serviceSessionData;

    private AtomicBoolean executing;
    private AtomicBoolean restart;
    private TimeLine timeLine;

    public SessionDataStatefulBean() {
        reset();
    }

    @PostConstruct
    public void init() {
        reset();
    }

    @PreDestroy
    public void destroy(){
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Worker> getWorkerList() {
        return workerList;
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

    public SimulableAdministrator getSimulableAdministrator() {
        return simulableAdministrator;
    }

    public SimulationIOController getSimulationIOController() {
        return simulationIOController;
    }

    public void initTimeLine(List<Simulable> simulableList){
        timeLine = new TimeLine(simulableList);
        simulationIOController = new SimulationIOController(timeLine.getSimulableList(),new SimulationCommitter());
        restart.set(false);
    }

    public void reset() {
        initSimulables();
        initSimulator();
        initSettings();
    }

    public void initSimulator() {
        simulableAdministrator = new SimulableAdministrator(new SimulationCommitter());
        restart = new AtomicBoolean(true);
        executing = new AtomicBoolean(true);
    }

    public void initSimulables() {
        companyList = new CopyOnWriteArrayList<>();
        clientList = new CopyOnWriteArrayList<>();
        workerList = new CopyOnWriteArrayList<>();
        followedSimulables = new LinkedList<>();
    }

    private void initSettings() {
        generalDataSettings = new GeneralSettingsStatefulBean();
        billDataSettings = new BillSettingsStatefulBean();
        clientDataSettings = new ClientSettingsStatefulBean();
        restaurantDataSettings = new RestaurantSettingsStatefulBean();
        providerDataSettings = new ProviderSettingsStatefulBean();
        serviceSessionData = new ServiceSessionData();
    }

    public GeneralSettingsStatefulBean getGeneralDataSettings() {
        return generalDataSettings;
    }

    public BillSettingsStatefulBean getBillDataSettings() {
        return billDataSettings;
    }

    public ClientSettingsStatefulBean getClientDataSettings() {
        return clientDataSettings;
    }

    public RestaurantSettingsStatefulBean getRestaurantDataSettings() {
        return restaurantDataSettings;
    }

    public ProviderSettingsStatefulBean getProviderDataSettings() {
        return providerDataSettings;
    }

    public ServiceSessionData getServiceSessionData() {
        return serviceSessionData;
    }
}
