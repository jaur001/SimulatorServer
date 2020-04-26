package backend.model.simulation.administration;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.sockets.PersonWebSocket;
import backend.utils.MathUtils;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulator{

    private static AtomicBoolean executing;
    private static AtomicBoolean restart = new AtomicBoolean(true);

    private static String uriProvider;
    private static String uriClient;
    private static TimeLine timeLine;
    private static SimulableAdministrator simulableAdministrator;
    private static SimulationAdministrator simulationAdministrator;

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

    public static void restart(){
        restart.set(true);
    }

    public static boolean isRunning() {
        return executing.get();
    }


    public static void changeExecuting() {
        if(isRunning()) stopSimulation();
        else startSimulation();
    }

    public static void stopSimulation() {
        executing.set(false);
    }

    public static void startSimulation(){
        executing.set(true);
    }

    public static boolean isNotInitialized(){
        return restart.get();
    }


    public static void startStop(boolean thread){
        if(Simulator.isNotInitialized()) Simulator.initExecution(thread);
        else Simulator.changeExecuting();
    }

    private static void initExecution(boolean thread) {
        initSimulatorElements();
        //SimulatorTester.test();
        if(thread) executeWithThread();
        else executeNormal();
    }

    private static void initSimulatorElements() {
        executing = new AtomicBoolean(true);
        restart = new AtomicBoolean(false);
        simulableAdministrator = new SimulableAdministrator(new SimulationCommitter());
        List<Simulable> simulables = Simulation.init();
        timeLine = new TimeLine(simulables);
        simulationAdministrator = new SimulationAdministrator(timeLine.getSimulableList(),new SimulationCommitter());
    }

    private static void executeWithThread() {
        ThreadPoolExecutor executor = SimulatorThreadPool.getExecutor();
        executor.submit(Simulator::executeNormal);
    }

    private static void executeNormal() {
        while (!restart.get()) {
            if (isRunning()) {
                timeLine.play();
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
        return timeLine;
    }

    public static void diePerson(Client client) {
        simulationAdministrator.diePerson(client);
    }

    public static void closeCompany(Company company) {
        simulationAdministrator.closeCompany(company);
    }
}
