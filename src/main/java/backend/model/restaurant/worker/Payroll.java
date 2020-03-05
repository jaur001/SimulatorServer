package backend.model.restaurant.worker;

import backend.model.restaurant.Restaurant;
import backend.time.Time;

import java.util.Date;

public class Payroll {
    private Worker worker;
    private Restaurant restaurant;
    private Date date;

    public Payroll(Worker worker, Restaurant restaurant) {
        this.worker = worker;
        this.restaurant = restaurant;
        date = Time.getActualDate();
    }

    public Worker getWorker() {
        return worker;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Date getDate() {
        return date;
    }
}
