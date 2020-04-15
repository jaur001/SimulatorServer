package backend.model.event.events.restaurant;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;

public class WorkerFiredRestaurantEvent extends GenericEvent<Restaurant> {

    private Worker worker;

    public WorkerFiredRestaurantEvent(Restaurant restaurant, Worker worker) {
        super(restaurant);
        this.worker = worker;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " has fired the " + worker.getJob() +": " + worker.getFullName()
                + " with Quality " + worker.getQuality() + "and Salary: " + worker.getSalary() + ".";
    }
}
