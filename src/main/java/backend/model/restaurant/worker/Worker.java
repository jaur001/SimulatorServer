package backend.model.restaurant.worker;

public class Worker {
    private double salary;
    private Job job;

    public Worker(double salary, Job job) {
        this.salary = salary;
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public Job getJob() {
        return job;
    }
}
