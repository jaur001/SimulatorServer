package backend.model.simulables.person.worker;

import backend.model.simulables.Simulable;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.person.Person;
import backend.model.simulables.person.PersonalData;
import backend.model.simulables.company.restaurant.Restaurant;

import java.util.concurrent.atomic.AtomicBoolean;

public class Worker extends Person implements Simulable, EconomicAgent {
    private double salary;
    private Quality quality;
    private AtomicBoolean isWorking = new AtomicBoolean(false);
    private Restaurant restaurant = null;

    public Worker(PersonalData personalData) {
        super(personalData);
        super.setJob("");
        salary = 0;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public boolean isWorking() {
        return isWorking.get();
    }

    public void hire(Restaurant restaurant, double salary) {
        this.restaurant = restaurant;
        this.salary = salary;
        this.isWorking.set(true);
    }

    public void fire() {
        restaurant = null;
        salary = 0;
        this.isWorking.set(false);
    }

    @Override
    public void simulate() {
    }


    @Override
    public void pay(double amount) {

    }

    @Override
    public void collect(double amount) {

    }
}
