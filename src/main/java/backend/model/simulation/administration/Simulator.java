package backend.model.simulation.administration;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.EJB.SessionDataStatefulBean;
import backend.server.EJB.TimerStatelessBean;
import backend.server.EJB.dataSettings.dataSettingsEJB.*;
import backend.utils.MathUtils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulator{

    private static String uriProvider;
    private static String uriClient;
    private static TimerStatelessBean timer;

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

    public static SessionDataStatefulBean getSessionData(){
        if(SessionAdministrator.getSessionList().isEmpty())
            SessionAdministrator.getSessionList().addSession();
        return SessionAdministrator.getSessionData();
    }

    public static GeneralSettingsStatefulBean getGeneralDataSettings() {
        return getSessionData().getGeneralDataSettings();
    }

    public static RestaurantSettingsStatefulBean getRestaurantDataSettings() {
        return getSessionData().getRestaurantDataSettings();
    }

    public static ClientSettingsStatefulBean getClientDataSettings() {
        return getSessionData().getClientDataSettings();
    }

    public static ProviderSettingsStatefulBean getProviderDataSettings() {
        return getSessionData().getProviderDataSettings();
    }

    public static BillSettingsStatefulBean getBillDataSettings() {
        return getSessionData().getBillDataSettings();
    }

    private static AtomicBoolean getRestart() {
        return getSessionData().getRestart();
    }

    private static AtomicBoolean getExecuting(){
        return getSessionData().getExecuting();
    }

    private static SimulableAdministrator getSimulableAdministrator(){
        return getSessionData().getSimulableAdministrator();
    }

    private static SimulationAdministrator getSimulationAdministrator(){
        return getSessionData().getSimulationAdministrator();
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
        return getRestart().get();
    }


    public static void startStop(boolean thread){
        if(Simulator.isNotInitialized()) Simulator.initExecution(thread);
        else Simulator.changeExecuting();
    }

    private static void initExecution(boolean thread) {
        initSimulatorElements();
        //SimulatorTester.test();
        if(thread) executeWithThread();
        else executeLocal();
    }


    private static void initSimulatorElements() {
        List<Simulable> simulables = initSimulables();
        getSessionData().initTimeLine(simulables);
    }

    private static List<Simulable> initSimulables() {
        return Simulation.init();
    }

    private static void executeWithThread() {
        try {
            if(timer==null)timer = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/TimerStatelessEJB");
            timer.setTimer();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        /*ThreadPoolExecutor executor = SimulatorThreadPool.getExecutor();
        executor.submit(() ->{

        });*/
    }


    private static void executeLocal() {
        while (!getRestart().get()) {
            if (isRunning()) {
                getTimeLine().play();
                delay();
                getSimulationAdministrator().manageSimulation();
            }
        }
        Simulation.reset();
    }

    private static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(TimeLine.TIMEOUT);
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

    public static boolean isNotAlreadyHired(Worker worker) {
        return getSimulableAdministrator().isNotAlreadyHired(worker);
    }

    public static boolean isNotAlreadyRetired(Worker worker) {
        return getSimulableAdministrator().isNotAlreadyRetired(worker);
    }

    public static void retire(Worker worker) {
        getSimulableAdministrator().retire(worker);
    }

    public static void addSimulableForCompany(ComplexCompany company, Simulable simulable) {
        getSimulableAdministrator().addSimulableForCompany(company,simulable);
    }

    public static void removeSimulableForCompany(ComplexCompany company, Simulable simulable) {
        getSimulableAdministrator().removeSimulableForCompany(company,simulable);
    }

    public static void makeChanges() {
        getSimulableAdministrator().makeChanges();
    }
    public static void isGoingToDie(Client client){
        getSimulableAdministrator().isGoingToDie(client);
    }

    public static void isGoingToClose(Company company){
        getSimulableAdministrator().isGoingToClose(company);
    }

    public static TimeLine getTimeLine() {
        return getSessionData().getTimeLine();
    }

    public static void diePerson(Client client) {
        getSimulationAdministrator().diePerson(client);
    }

    public static void closeCompany(Company company) {
        getSimulationAdministrator().closeCompany(company);
    }
}
