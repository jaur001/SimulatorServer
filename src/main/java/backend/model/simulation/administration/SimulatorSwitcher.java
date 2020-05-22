package backend.model.simulation.administration;

import backend.model.simulables.Simulable;
import backend.model.simulation.timeLine.TimeLine;
import backend.utils.MathUtils;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimulatorSwitcher {

    private static String uriProvider;
    private static String uriClient;

    public static String getUriProvider() {
        return uriProvider;
    }

    public static void setUriProvider(String uriProvider) {
        SimulatorSwitcher.uriProvider = uriProvider;
    }

    public static String getUriClient() {
        return uriClient;
    }

    public static void setUriClient(String uriClient) {
        SimulatorSwitcher.uriClient = uriClient;
    }

    public static void restart(){
        SimulationDataController.getRestart().set(true);
    }

    public static boolean isRunning() {
        return SimulationDataController.getExecuting().get();
    }


    public static void changeExecuting() {
        if(isRunning()) stopSimulation();
        else startSimulation();
    }

    public static void stopSimulation() {
        SimulationDataController.getExecuting().set(false);
    }

    public static void startSimulation(){
        SimulationDataController.getExecuting().set(true);
    }

    public static boolean isNotInitialized(){
        return SimulationDataController.getRestart().get();
    }


    public static void startStop(boolean thread){
        if(SimulationDataController.getSessionData() == null) initExecution(thread);
        if(SimulatorSwitcher.isNotInitialized()) SimulatorSwitcher.initExecution(thread);
        else SimulatorSwitcher.changeExecuting();
    }

    private static void initExecution(boolean thread) {
        SimulationDataController.initSessionData();
        initSimulatorElements();
        //SimulatorTester.test();
        if(thread) executeWithThread();
        else executeLocal();
    }


    private static void initSimulatorElements() {
        List<Simulable> simulables = initSimulables();
        SimulationDataController.getSessionData().initTimeLine(simulables);
    }

    private static List<Simulable> initSimulables() {
        return Simulation.init();
    }

    private static void executeWithThread() {
        /*try {
            if(timer==null)timer = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/TimerStatelessEJB");
            timer.setTimer();
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
        ThreadPoolExecutor executor = SimulatorThreadPool.getExecutor();
        executor.submit(SimulatorSwitcher::executeLocal);
    }


    private static void executeLocal() {
        while (!SimulationDataController.getRestart().get()) {
            if (isRunning()) {
                SimulationDataController.getTimeLine().play();
                SimulationDataController.getSessionData().getSimulationIOController().manageSimulation();
                delay();
            }
        }
        Simulation.reset();
    }

    private static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(TimeLine.getTimeOut());
        } catch (InterruptedException e) {
            System.out.println("Simulation stopped");
        }
    }

    public static void waitForOtherElements() {
        try {
            TimeUnit.MILLISECONDS.sleep(MathUtils.random(100,500));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
