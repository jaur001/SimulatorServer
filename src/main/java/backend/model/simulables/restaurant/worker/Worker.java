package backend.model.simulables.restaurant.worker;

import backend.model.NIFCreator.PersonNIFCreator;

public class Worker {
    private int NIF;
    private double salary;
    private Job job;

    public Worker(double salary, Job job) {
        NIF = new PersonNIFCreator().create();
        this.salary = salary;
        this.job = job;
    }

    public int getNIF() {
        return NIF;
    }

    public double getSalary() {
        return salary;
    }

    public Job getJob() {
        return job;
    }
}
