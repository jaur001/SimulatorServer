package backend.model.event.events.restaurant;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;

public class WorkerRetiredRestaurantEvent extends GenericEvent<Restaurant> {

    private Worker worker;

    public WorkerRetiredRestaurantEvent(Restaurant restaurant, Worker worker) {
        super(restaurant);
        this.worker = worker;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " has retired the " + worker.getJob() +": " + worker.getFullName()
                + " with a pension of " + worker.getSalary() + ".";
    }
}