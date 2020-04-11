package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.event.Event;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.BillSettings;

public class Payroll extends CFDIBill implements Event {
    private static final Type type = Type.payroll;
    private Worker worker;
    private Company company;
    public Payroll(Worker worker, Company company) {
        super(company.getStreet(),type,company.getCompanyName(),company.getNIF(),worker.getFullName(),worker.getNIF(),worker.getSalary(), BillSettings.getConcept("Payroll"));
        this.worker = worker;
        this.company = company;
    }

    public Worker getWorker() {
        return worker;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public String getMessage() {
        return "The " + this.getWorker().getJob() + ":"
                + this.getReceiverName() + " has received the payroll from "
                + this.getIssuerName() +", amount: " +this.getTotal();
    }
}
