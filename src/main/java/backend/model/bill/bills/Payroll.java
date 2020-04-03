package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.BillSettings;

public class Payroll extends CFDIBill {
    private static final Type type = Type.payroll;
    private Worker worker;
    private Restaurant restaurant;
    public Payroll(Worker worker, Restaurant restaurant) {
        super(restaurant.getStreet(),type,restaurant.getName(),restaurant.getNIF(),worker.getFullName(),worker.getNIF(),worker.getSalary(), BillSettings.getConcept("Payroll"));
        this.worker = worker;
        this.restaurant = restaurant;
    }

    public Worker getWorker() {
        return worker;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
