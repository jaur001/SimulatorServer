package backend.model.simulation.simulator;

import backend.implementations.SQLite.SQLiteDatabaseConnector;
import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.SimulableAdministrator;
import backend.model.simulation.timeLine.TimeLine;
import backend.utils.MathUtils;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulator{

    private static AtomicBoolean executing;
    private static AtomicBoolean restart = new AtomicBoolean(false);

    private static String uriProvider;
    private static String uriClient;
    private static TimeLine timeLine;
    private static SimulableAdministrator helper;

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
        executing = null;
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
        try {
            new SQLiteDatabaseConnector().disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void startSimulation(){
        executing.set(true);
        try {
            new SQLiteDatabaseConnector().connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInitialized(){
        return executing != null;
    }


    public static void startStop(boolean thread){
        if(!Simulator.isInitialized()) Simulator.execute(thread);
        else Simulator.changeExecuting();
    }

    private static void execute(boolean thread) {
        executing = new AtomicBoolean(true);
        restart = new AtomicBoolean(false);
        helper = new SimulableAdministrator();
        timeLine = new TimeLine(Simulation.init());
        helper.setTimeLine(timeLine);
        //SimulatorTester.test();
        if(thread) executeWithThread();
        else executeNormal();
    }

    private static void executeWithThread() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        executor.submit(Simulator::executeNormal);
    }

    private static void executeNormal() {
        while (!restart.get()){
            if(isRunning()){
                timeLine.play();
            }
        }
    }

    public static void waitForOtherElements() {
        try {
            TimeUnit.MILLISECONDS.sleep(MathUtils.random(100,500));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isNotAlreadyHired(Worker worker) {
        return helper.isNotAlreadyHired(worker);
    }

    public static boolean isNotAlreadyRetired(Worker worker) {
        return helper.isNotAlreadyRetired(worker);
    }

    public static void retire(Worker worker) {
        helper.retire(worker);
    }

    public static void addSimulableForCompany(Company company, Simulable simulable) {
        helper.addSimulableForCompany(company,simulable);
    }

    public static void removeSimulableForCompany(Company company, Simulable simulable) {
        helper.removeSimulableForCompany(company,simulable);
    }

    public static void makeChanges() {
        helper.makeChanges();
    }
    public static void isGoingToDie(Client client){
        helper.isGoingToDie(client);
    }

    public static void isGoingToClose(Company company){
        helper.isGoingToClose(company);
    }

    public static TimeLine getTimeLine() {
        return timeLine;
    }
}
