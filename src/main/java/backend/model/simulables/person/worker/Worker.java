package backend.model.simulables.person.worker;

import backend.model.event.Event;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.client.PersonalData;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulables.person.worker.jobSearcher.OfferSelector;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker extends Client implements Event,Cloneable {
    private double salaryDesired;
    private Quality quality;
    private AtomicBoolean isWorking = new AtomicBoolean(false);
    private Restaurant restaurant = null;
    private List<JobOffer> jobOfferList;


    public Worker(PersonalData personalData) {
        super(personalData);
        super.setJob("");
        salaryDesired = 0;
        setSalary(0);
        jobOfferList = new LinkedList<>();
    }


    public Restaurant getRestaurant() {
        return restaurant;
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

    @Override
    public void setJob(String job){
        super.setJob(job);
        if (job.equals("Retired")) salaryDesired = getSalary();
        else salaryDesired = RestaurantSettings.getSalary(Job.valueOf(job));
    }

    public boolean isWorking() {
        return isWorking.get();
    }

    public void hire(Restaurant restaurant, double salary) {
        this.isWorking.set(true);
        this.restaurant = restaurant;
        setSalary(salary);
        salaryDesired = getSalary();
        this.jobOfferList = new LinkedList<>();
        addEvent(this);
    }

    public void fire() {
        this.isWorking.set(false);
        restaurant = null;
        salaryDesired = getSalary();
        setSalary(0);
        routineList = null;
        addEvent(this);
    }

    public void retire() {
        this.isWorking.set(false);
        restaurant = null;
        setSalary(Math.max(getSalary()* WorkerSettings.PERCENTAGE_RETIREMENT, ClientSettings.getMinSalary()));
        setJob("Retired");
        addEvent(this);
    }

    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        if (isNotRetired())work();
        if(isWorking() || !isNotRetired()) enjoyTime();
    }

    private void work() {
        if(WorkerSettings.isInRetireAge(this)&&!isWorking()) retire();
        else if(!isWorking()) searchJob();
    }

    private void enjoyTime() {
        if(routineList==null) routineList = new RoutineList(getSalary(),ClientSettings.getRoutineList(getSalary()));
        super.simulate();
    }


    public boolean isNotRetired() {
        return !getJob().equals("Retired");
    }

    public void searchJob() {
        if (jobOfferList.stream().anyMatch(JobOffer::isAccepted)) return;
        jobOfferList.stream().filter(JobOffer::isCanceled).forEach(jobOfferList::remove);
        if(!new OfferSelector(jobOfferList,Simulation.SEARCHER_STRATEGY).searchJob()) reduceSalaryDesired();
    }

    private void reduceSalaryDesired() {
        salaryDesired = WorkerSettings.reduceSalaryDesired(salaryDesired);
    }

    public void addOffer(JobOffer jobOffer) {
        if(!jobOfferList.contains(jobOffer))jobOfferList.add(jobOffer);
    }

    @Override
    public String getMessage() {
        if(!Simulation.getWorkerList().contains(this)) super.getMessage();
        if(!isNotRetired())return getFullName() + " has retired.";
        if(isWorking()) return getFullName() + " has been hired for a salary of "+ getSalary() + "€.";
        return getFullName() + " has been fired.";
    }
}
