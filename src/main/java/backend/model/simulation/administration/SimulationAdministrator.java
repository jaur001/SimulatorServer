package backend.model.simulation.administration;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.server.EJB.SessionDataStatefulBean;

public class SimulationAdministrator {


    private static SimulableAdministrator getSimulableAdministrator(){
        return SimulationDataController.getSessionData().getSimulableAdministrator();
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
}
