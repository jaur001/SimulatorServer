package backend.model.simulation.administration;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimulatorThreadPool {

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static ThreadPoolExecutor getExecutor(){
        return executor;
    }
}
