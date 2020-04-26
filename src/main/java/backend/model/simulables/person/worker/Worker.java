package backend.model.simulables.person.worker;

import backend.model.simulables.SimulableTester;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.client.PersonalData;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulables.person.worker.jobSearcher.OfferSelector;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.Simulator;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker extends Client{
    private double salaryDesired;
    private Quality quality;
    private AtomicBoolean isWorking = new AtomicBoolean(false);
    private ComplexCompany company = null;
    private List<JobOffer> jobOfferList;


    public Worker(PersonalData personalData) {
        super(personalData);
        super.setJob("");
        salaryDesired = 0;
        setSalary(0);
        jobOfferList = new CopyOnWriteArrayList<>();
    }


    public ComplexCompany getCompany() {
        return company;
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

    public void hire(ComplexCompany company, double salary) {
        this.isWorking.set(true);
        this.company = company;
        setSalary(salary);
        salaryDesired = getSalary();
        this.jobOfferList = new LinkedList<>();
    }

    public void fire() {
        this.isWorking.set(false);
        company = null;
        salaryDesired = getSalary();
        setSalary(0);
        routineList = null;
    }

    public void retire() {
        this.isWorking.set(false);
        company = null;
        setPension();
        setJob("Retired");
    }

    public void setPension() {
        setSalary(Math.max(getSalary()* WorkerSettings.PERCENTAGE_RETIREMENT, ClientSettings.getMinSalary()));
    }

    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        if (!isRetired())work();
        if(isWorking() || !isRetired()) enjoyTime();
    }

    private void work() {
        if(WorkerSettings.isInRetireAge(this)&&!isWorking()) Simulator.retire(this);
        else if(!isWorking()) searchJob();
    }

    private void enjoyTime() {
        if(routineList==null) routineList = new RoutineList(getSalary(),ClientSettings.getRoutineList(getSalary()));
        super.simulate();
    }


    public boolean isRetired() {
        return getJob().equals("Retired");
    }

    public void searchJob() {
        try {
            if(jobOfferList.size()>0)jobOfferList.stream().filter(JobOffer::isCanceled).forEach(jobOfferList::remove);
        } catch (ConcurrentModificationException e){
            Simulator.waitForOtherElements();
            searchJob();
        }
        if (jobOfferList.stream().anyMatch(JobOffer::isAccepted)) return;
        if(!new OfferSelector(jobOfferList,Simulation.SEARCHER_STRATEGY).searchJob()) reduceSalaryDesired();
    }

    private void reduceSalaryDesired() {
        salaryDesired = WorkerSettings.reduceSalaryDesired(salaryDesired);
    }

    public void addOffer(JobOffer jobOffer) {
        if(!jobOfferList.contains(jobOffer))jobOfferList.add(jobOffer);
    }

}