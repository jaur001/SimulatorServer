package backend.model.simulables.person.worker;

import backend.model.simulables.company.ComplexCompany;

public class JobOffer {
    private ComplexCompany company;
    private Worker worker;
    private double salary;
    private boolean canceled;
    private boolean acceptedByWorker;
    private boolean acceptedByRestaurant;

    public JobOffer(ComplexCompany company, Worker worker, double salary) {
        this.company = company;
        this.worker = worker;
        this.salary = salary;
        canceled = false;
        acceptedByWorker = false;
        acceptedByRestaurant = false;
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
        return acceptedByWorker && acceptedByRestaurant;
    }

    public void acceptOfferWorker() {
        acceptedByWorker = true;
    }

    public void acceptOfferRestaurant() {
        acceptedByRestaurant = true;
    }

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
