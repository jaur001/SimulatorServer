package backend.model.simulables.person.worker;

import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.simulables.Simulable;
import backend.model.simulables.person.Person;
import backend.model.simulables.person.PersonalData;

import java.util.concurrent.atomic.AtomicBoolean;

public class Worker extends Person implements Simulable {
    private double salary;
    private Quality quality;
    private AtomicBoolean isWorking = new AtomicBoolean(false);

    public Worker(PersonalData personalData) {
        super(personalData);
        super.setJob("");
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

    public void hire() {
        this.isWorking.set(true);
    }

    public void fire() {
        this.isWorking.set(false);
    }

    @Override
    public void simulate() {

    }
}
