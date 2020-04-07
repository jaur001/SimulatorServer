package backend.model.simulables.person.worker;

import backend.model.simulables.Simulable;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.person.Person;
import backend.model.simulables.person.PersonalData;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.jobSearcher.JobSearcher;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker extends Person implements Simulable, EconomicAgent {
    private double salary;
    private double salaryDesired;
    private Quality quality;
    private AtomicBoolean isWorking = new AtomicBoolean(false);
    private Restaurant restaurant = null;
    private List<JobOffer> jobOfferList;

    public Worker(PersonalData personalData) {
        super(personalData);
        super.setJob("");
        salary = 0;
        salaryDesired = 0;
        jobOfferList = new LinkedList<>();
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

    public double getSalaryDesired() {
        return salaryDesired;
    }

    public void setSalaryDesired(double salaryDesired) {
        this.salaryDesired = salaryDesired;
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
        this.salaryDesired = 0;
        this.isWorking.set(true);
        this.jobOfferList = new LinkedList<>();
    }

    public void fire() {
        restaurant = null;
        salaryDesired = salary;
        salary = 0;
        this.isWorking.set(false);
    }

    @Override
    public void simulate() {
        if(!isWorking.get()) new JobSearcher(jobOfferList,salaryDesired, Simulation.SEARCHER_STRATEGY).searchJob();
    }




    @Override
    public void pay(double amount) {

    }

    @Override
    public void collect(double amount) {

    }

    public void addOffer(JobOffer jobOffer) {
        if(!jobOfferList.contains(jobOffer))jobOfferList.add(jobOffer);
    }
}
