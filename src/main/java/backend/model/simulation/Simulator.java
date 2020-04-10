package backend.model.simulation;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.model.simulables.Simulable;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.timeLine.TimeLine;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
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
        timeLine = new TimeLine(Simulation.init());
        if(thread) executeWithThread();
        else executeNormal();
        test();
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

    private static void test() {
        ThreadPoolExecutor tester = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        tester.submit(() -> {
            while (!restart.get()){
                if(isRunning()){
                    checkProgram();
                }
            }
        });
    }

    private static void checkProgram() {
        Date date = (Date) TimeLine.getDate().clone();
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if(TimeLine.isSameDate(date)){
            Simulable simulable = SimulableTester.actualSimulable;
            List<Client> clients = Simulation.getClientList();
            List<Worker> workers = Simulation.getWorkerList();
            List<Restaurant> restaurants = Simulation.getRestaurantList();
            List<Provider> providers = Simulation.getProviderList();
            System.out.println("Problem");
        }
    }


    public static void waitForDatabaseOrThread() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void diePerson(Client client) {
        if(client instanceof Worker) Simulation.removeWorker((Worker) client);
        else Simulation.removeClient(client);
        timeLine.removeSimulable(client);

    }

}
