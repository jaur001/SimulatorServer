package backend.model.simulation;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.model.simulation.timeLine.TimeLine;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulator {

    private static AtomicBoolean executing;
    private static AtomicBoolean restart;

    private static String uriProvider;
    private static String uriClient;

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


    public static void startStop(){
        if(!Simulator.isInitialized()) Simulator.execute();
        else Simulator.changeExecuting();
    }

    private static void execute() {
        executing = new AtomicBoolean(true);
        restart = new AtomicBoolean(false);
        TimeLine timeLine = new TimeLine(Simulation.init());
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            while (!restart.get()){
                if(isRunning()){
                    timeLine.play();
                }
            }
        });
    }

    public static void waitForDatabaseOrThread() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
