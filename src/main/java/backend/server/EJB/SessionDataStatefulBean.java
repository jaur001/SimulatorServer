package backend.server.EJB;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.SimulableAdministrator;
import backend.model.simulation.administration.SimulationAdministrator;
import backend.model.simulation.administration.SimulationCommitter;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.EJB.dataSettings.dataSettingsEJB.*;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
    private SimulationAdministrator simulationAdministrator;

    private GeneralSettingsStatefulBean generalDataSettings;
    private RestaurantSettingsStatefulBean restaurantDataSettings;
    private ClientSettingsStatefulBean clientDataSettings;
    private ProviderSettingsStatefulBean providerDataSettings;
    private BillSettingsStatefulBean billDataSettings;

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

    public SimulationAdministrator getSimulationAdministrator() {
        return simulationAdministrator;
    }

    public void initTimeLine(List<Simulable> simulableList){
        timeLine = new TimeLine(simulableList);
        simulationAdministrator = new SimulationAdministrator(timeLine.getSimulableList(),new SimulationCommitter());
        restart.set(false);
    }

    public void reset() {
        companyList = new CopyOnWriteArrayList<>();
        clientList = new CopyOnWriteArrayList<>();
        workerList = new CopyOnWriteArrayList<>();
        followedSimulables = new LinkedList<>();
        simulableAdministrator = new SimulableAdministrator(new SimulationCommitter());
        restart = new AtomicBoolean(true);
        executing = new AtomicBoolean(true);
        initSettings();
    }

    private void initSettings() {
        try {
            generalDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/GeneralSettingsStatefulEJB");
            restaurantDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/RestaurantSettingsStatefulEJB");
            clientDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/ClientSettingsStatefulEJB");
            providerDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/ProviderSettingsStatefulEJB");
            billDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/BillSettingsStatefulEJB");
        } catch (NamingException e) {
            initGeneralSettings();
            initRestaurantSettings();
            initClientSettings();
            initProviderSettings();
            initBillSettings();
        }
    }

    private void initGeneralSettings() {
        generalDataSettings = new GeneralSettingsStatefulBean();
        generalDataSettings.initSettings();
    }

    private void initClientSettings() {
        clientDataSettings = new ClientSettingsStatefulBean();
        clientDataSettings.initSettings();
    }

    private void initProviderSettings() {
        providerDataSettings = new ProviderSettingsStatefulBean();
        providerDataSettings.initSettings();
    }

    private void initRestaurantSettings() {
        restaurantDataSettings = new RestaurantSettingsStatefulBean();
        restaurantDataSettings.initSettings();
    }

    private void initBillSettings() {
        billDataSettings = new BillSettingsStatefulBean();
        billDataSettings.initSettings();
    }

    public GeneralSettingsStatefulBean getGeneralDataSettings() {
        return generalDataSettings;
    }

    public RestaurantSettingsStatefulBean getRestaurantDataSettings() {
        return restaurantDataSettings;
    }

    public ClientSettingsStatefulBean getClientDataSettings() {
        return clientDataSettings;
    }

    public ProviderSettingsStatefulBean getProviderDataSettings() {
        return providerDataSettings;
    }

    public BillSettingsStatefulBean getBillDataSettings() {
        return billDataSettings;
    }
}
