package backend.model.simulation.administration.centralControl;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.data.SimulationDataAdministrator;
import backend.model.simulation.administration.simulableControl.SimulableController;

public class SimulationAdministrator {

    public static void manageSimulation() {
        getSimulableController().manageSimulation();
    }

    private static SimulableController getSimulableController(){
        return SimulationDataAdministrator.getSimulationData().getSimulableController();
    }

    public static boolean isNotAlreadyHired(Worker worker) {
        return getSimulableController().isNotAlreadyHired(worker);
    }

    public static boolean isNotAlreadyRetired(Worker worker) {
        return getSimulableController().isNotAlreadyRetired(worker);
    }

    public static void retire(Worker worker) {
        getSimulableController().retire(worker);
    }

    public static void addSimulableForCompany(ComplexCompany company, Simulable simulable) {
        getSimulableController().addSimulableForCompany(company,simulable);
    }

    public static void removeSimulableForCompany(ComplexCompany company, Simulable simulable) {
        getSimulableController().removeSimulableForCompany(company,simulable);
    }

    public static void makeChanges() {
        getSimulableController().makeChanges();
    }

    public static void isGoingToDie(Client client){
        getSimulableController().isGoingToDie(client);
    }

    public static void isGoingToClose(Company company){
        getSimulableController().isGoingToClose(company);
    }
}
