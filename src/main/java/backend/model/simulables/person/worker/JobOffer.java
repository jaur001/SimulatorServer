package backend.model.simulables.person.worker;

import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.WorkerSettings;

public class JobOffer {
    private Restaurant restaurant;
    private Worker worker;
    private double salary;
    private boolean accepted;

    public JobOffer(Restaurant restaurant, Worker worker, double salary) {
        this.restaurant = restaurant;
        this.worker = worker;
        this.salary = salary;
        accepted = false;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Worker getWorker() {
        return worker;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isAccepted(){
        return accepted;
    }

    public void acceptOffer() {
        accepted = true;
    }
}
