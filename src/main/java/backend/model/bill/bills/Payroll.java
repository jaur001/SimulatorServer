package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulables.restaurant.Restaurant;
import backend.model.simulables.restaurant.worker.Worker;

public class Payroll extends CFDIBill {
    private static final Type type = Type.payroll;
    public Payroll(Worker worker, Restaurant restaurant) {
        super(restaurant.getStreet(),type,restaurant.getName(),restaurant.getNIF(),worker.getJob().toString(),worker.getNIF(),worker.getSalary());
    }

}
