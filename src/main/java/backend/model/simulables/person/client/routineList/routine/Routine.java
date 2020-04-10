package backend.model.simulables.person.client.routineList.routine;

import backend.model.simulables.company.restaurant.Restaurant;

public class Routine {
    private Restaurant restaurant;
    private Counter counter;

    public Routine(Restaurant restaurant, Counter counter) {
        this.restaurant = restaurant;
        this.counter = counter;
    }

    public boolean check(){
        return counter.countDown();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getCount() {
        return counter.getCount();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
