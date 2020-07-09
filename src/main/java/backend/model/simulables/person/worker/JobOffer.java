package backend.model.simulables.person.worker;

import backend.model.simulables.company.complexCompany.ComplexCompany;

import java.util.concurrent.atomic.AtomicBoolean;

public class JobOffer {
    private ComplexCompany company;
    private Worker worker;
    private double salary;
    private AtomicBoolean canceled;
    private AtomicBoolean acceptedByWorker;
    private AtomicBoolean acceptedByRestaurant;

    public JobOffer(ComplexCompany company, Worker worker, double salary) {
        this.company = company;
        this.worker = worker;
        this.salary = salary;
        canceled = new AtomicBoolean(false);
        acceptedByWorker = new AtomicBoolean(false);
        acceptedByRestaurant = new AtomicBoolean(false);
    }

    public ComplexCompany getCompany() {
        return company;
    }

    public Worker getWorker() {
        return worker;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isAccepted(){
        return acceptedByWorker.get() && acceptedByRestaurant.get();
    }

    public void acceptOfferWorker() {
        acceptedByWorker.set(true);
    }

    public void acceptOfferRestaurant() {
        acceptedByRestaurant.set(true);
    }

    public void cancel() {
        canceled.set(true);
    }

    public boolean isCanceled() {
        return canceled.get();
    }
}
