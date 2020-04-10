package backend.model.simulation.timeLine;

import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.LinkedList;
import java.util.List;

public class SimulationController extends EventGenerator {

    private List<Simulable> simulableList;
    private List<Simulable> addingList;
    private List<Simulable> removingList;


    public SimulationController(List<Simulable> simulableList) {
        this.simulableList = simulableList;
        this.addingList = new LinkedList<>();
        this.removingList = new LinkedList<>();
    }

    public void manageSimulation() {
        checkThereIsNewPerson();
        checkThereIsNewCompany();
        checkAddingList();
        checkRemovingList();

    }

    private void checkThereIsNewPerson() {
        checkThereIsNewWorker();
        checkThereIsNewClient();
    }

    private void checkThereIsNewWorker() {
        if(!WorkerSettings.newWorker()) return;
        addSimulable(Simulation.addWorker());
    }

    private void checkThereIsNewClient() {
        if(!ClientSettings.newClient()) return;
        addSimulable(Simulation.addClient());
    }

    private void checkThereIsNewCompany() {
        checkThereIsNewRestaurant();
        checkThereIsNewProvider();
    }

    private void checkThereIsNewRestaurant() {
        if(!RestaurantSettings.newRestaurant()) return;
        addSimulable(Simulation.addRestaurant());
    }

    private void checkThereIsNewProvider() {
        if(!ProviderSettings.newProvider()) return;
        addSimulable(Simulation.addProvider());
    }

    private void checkAddingList() {
        if(addingList.size() > 0) simulableList.addAll(addingList);
        addingList = new LinkedList<>();
    }

    private void checkRemovingList() {
        if(removingList.size() > 0) simulableList.removeAll(removingList);
        removingList = new LinkedList<>();
    }

    public void addSimulable(Simulable simulable){
        if(simulable!=null)simulableList.add(simulable);
    }

    public void addSimulableAtTheEnd(Simulable simulable){
        addingList.add(simulable);
    }

    public void removeSimulableAtTheEnd(Simulable simulable) {
        checkSimulable(simulable);
        removingList.add(simulable);
    }

    private void checkSimulable(Simulable simulable) {
        if(simulable instanceof Client) addEvent((Client)simulable);
        if (simulable instanceof Company) addEvent((Company)simulable);
    }
}
