package backend.model.simulables.person.worker;

import backend.model.simulables.company.restaurant.Restaurant;

public class JobOffer {
    private Restaurant restaurant;
    private Worker worker;
    private double salary;
    private boolean canceled;
    private boolean acceptedByWorker;
    private boolean acceptedByRestaurant;

    public JobOffer(Restaurant restaurant, Worker worker, double salary) {
        this.restaurant = restaurant;
        this.worker = worker;
        this.salary = salary;
        canceled = false;
        acceptedByWorker = false;
        acceptedByRestaurant = false;
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
        return acceptedByWorker && acceptedByRestaurant;
    }

    public void acceptOfferWorker() {
        acceptedByWorker = true;
    }

    public void acceptOfferRestaurant() {
        acceptedByRestaurant = true;
    }

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
