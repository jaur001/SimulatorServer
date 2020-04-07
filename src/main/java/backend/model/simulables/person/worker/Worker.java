package backend.model.simulables.person.worker;

import backend.implementations.routine.GenericRoutineFactory;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.client.PersonalData;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.person.worker.jobSearcher.JobSearcher;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker extends Client{
    private double salaryDesired;
    private Quality quality;
    private AtomicBoolean isWorking = new AtomicBoolean(false);
    private AtomicBoolean isLocked = new AtomicBoolean(false);
    private Restaurant restaurant = null;
    private List<JobOffer> jobOfferList;

    public Worker(PersonalData personalData) {
        super(personalData);
        super.setJob("");
        salaryDesired = 0;
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
        salaryDesired = RestaurantSettings.getSalary(Job.valueOf(job));
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
        isLocked.set(false);
    }

    public void fire() {
        this.isWorking.set(false);
        restaurant = null;
        salaryDesired = getSalary();
        setSalary(0);
        routineList.deleteRoutines();
        isLocked.set(false);
    }

    public void retire() {
        this.isWorking.set(false);
        restaurant = null;
        setSalary(Math.max(getSalary()* WorkerSettings.PERCENTAGE_RETIREMENT,500));
        setJob("Retired");
    }

    @Override
    public void simulate() {
        if(!isWorking.get() && isNotRetired()) searchJob();
        else if(routineList.isEmpty()) routineList = new RoutineList(getSalary(),getRoutineList(getSalary()));
        else routineList.checkRoutines().forEach(this::goToEat);
    }

    public List<Routine> getRoutineList(double salary) {
        return new GenericRoutineFactory(Simulation.ROUTINE_STRATEGY,salary).createRoutineList();
    }

    public boolean isNotRetired() {
        return getSalary()==0;
    }

    public void searchJob() {
        jobOfferList.stream().filter(JobOffer::isCanceled).forEach(jobOfferList::remove);
        if(!new JobSearcher(jobOfferList,Simulation.SEARCHER_STRATEGY).searchJob()) reduceSalaryDesired();
    }

    private void reduceSalaryDesired() {
        salaryDesired = WorkerSettings.reduceSalaryDesired(salaryDesired);
    }

    public void addOffer(JobOffer jobOffer) {
        if(!jobOfferList.contains(jobOffer))jobOfferList.add(jobOffer);
    }

    public void lock() {
        isLocked.set(true);
    }

    public void unlock() {
        isLocked.set(false);
    }

    public boolean isLocked(){
        return isLocked.get();
    }
}
