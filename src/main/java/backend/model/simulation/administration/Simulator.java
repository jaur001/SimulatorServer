package backend.model.simulation.administration;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.EJB.SessionDataStatefulBean;
import backend.utils.MathUtils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulator{

    private static String uriProvider;
    private static String uriClient;
    private static SimulableAdministrator simulableAdministrator;
    private static SimulationAdministrator simulationAdministrator;
    private static SessionDataStatefulBean sessionData;

    public static String getUriProvider() {
        return uriProvider;
    }

    public static void setUriProvider(String uriProvider) {
        Simulator.uriProvider = uriProvider;
    }

    public static String getUriClient() {
        return uriClient;
    }

    public static void setUriClient(String uriClient) {
        Simulator.uriClient = uriClient;
    }

    private static AtomicBoolean getRestart() {
        return sessionData.getRestart();
    }

    private static AtomicBoolean getExecuting(){
        return sessionData.getExecuting();
    }

    public static void restart(){
        getRestart().set(true);
    }

    public static boolean isRunning() {
        return getExecuting().get();
    }


    public static void changeExecuting() {
        if(isRunning()) stopSimulation();
        else startSimulation();
    }

    public static void stopSimulation() {
        getExecuting().set(false);
    }

    public static void startSimulation(){
        getExecuting().set(true);
    }

    public static boolean isNotInitialized(){
        if (sessionData == null) return true;
        return getRestart().get();
    }


    public static void startStop(boolean thread){
        if(Simulator.isNotInitialized()) Simulator.initExecution(thread);
        else Simulator.changeExecuting();
    }

    private static void initExecution(boolean thread) {
        initSimulatorElements(thread);
        //SimulatorTester.test();
        if(thread) executeWithThread();
        else executeLocal();
    }

    private static void initSimulatorElements(boolean thread) {
        simulableAdministrator = new SimulableAdministrator(new SimulationCommitter());
        List<Simulable> simulables;
        if(thread) simulables = initWithSession();
        else simulables = initLocal();
        sessionData.initTimeLine(simulables);
        simulationAdministrator = new SimulationAdministrator(getTimeLine().getSimulableList(),new SimulationCommitter());
        sessionData.getRestart().set(false);
    }

    private static List<Simulable> initWithSession() {
        try {
            sessionData = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/SessionDataStatefulEJB");
            return Simulation.init(sessionData);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return new CopyOnWriteArrayList<>();
    }

    private static List<Simulable> initLocal() {
        sessionData = new SessionDataStatefulBean();
        return Simulation.init(sessionData);
    }

    private static void executeWithThread() {
        ThreadPoolExecutor executor = SimulatorThreadPool.getExecutor();
        executor.submit(Simulator::executeLocal);
    }

    private static void executeLocal() {
        while (!getRestart().get()) {
            if (isRunning()) {
                getTimeLine().play();
                simulationAdministrator.manageSimulation();
            }
        }
        Simulation.reset();
    }

    public static void waitForOtherElements() {
        try {
            TimeUnit.MILLISECONDS.sleep(MathUtils.random(100,500));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isNotAlreadyHired(Worker worker) {
        return simulableAdministrator.isNotAlreadyHired(worker);
    }

    public static boolean isNotAlreadyRetired(Worker worker) {
        return simulableAdministrator.isNotAlreadyRetired(worker);
    }

    public static void retire(Worker worker) {
        simulableAdministrator.retire(worker);
    }

    public static void addSimulableForCompany(ComplexCompany company, Simulable simulable) {
        simulableAdministrator.addSimulableForCompany(company,simulable);
    }

    public static void removeSimulableForCompany(ComplexCompany company, Simulable simulable) {
        simulableAdministrator.removeSimulableForCompany(company,simulable);
    }

    public static void makeChanges() {
        simulableAdministrator.makeChanges();
    }
    public static void isGoingToDie(Client client){
        simulableAdministrator.isGoingToDie(client);
    }

    public static void isGoingToClose(Company company){
        simulableAdministrator.isGoingToClose(company);
    }

    public static TimeLine getTimeLine() {
        return sessionData.getTimeLine();
    }

    public static void diePerson(Client client) {
        simulationAdministrator.diePerson(client);
    }

    public static void closeCompany(Company company) {
        simulationAdministrator.closeCompany(company);
    }
}
