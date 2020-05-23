package backend.model.simulation.administration;

import backend.model.simulables.Simulable;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.timeLine.TimeLine;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimulatorTester {

    public static void test() {
        ThreadPoolExecutor tester = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        tester.submit(SimulatorTester::checkProgram);
    }

    private static void checkProgram() {
        Date date = (Date) TimeLine.getDate().clone();
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if(TimeLine.isSameDate(date)){
            TimeLine timeLine = SimulationDataController.getTimeLine();
            Simulable simulable = SimulableTester.actualSimulable;
            int method = SimulableTester.method;
            Simulable actualSimulable = TimeLine.actualSimulable;
            List<Client> clients = Simulation.getClientListCopy();
            List<Worker> workers = Simulation.getWorkerListCopy();
            List<Restaurant> restaurants = Simulation.getRestaurantListCopy();
            List<Provider> providers = Simulation.getProviderListCopy();
            System.out.println("Problem");
        }
    }
}
