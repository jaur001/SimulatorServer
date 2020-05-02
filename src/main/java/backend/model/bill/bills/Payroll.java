package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.bill.Use;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.BillSettings;

public class Payroll extends CFDIBill{
    private static final Type type = Type.payroll;
    private static final Use use = Use.NO1;
    private Worker worker;
    private ComplexCompany company;
    public Payroll(ComplexCompany company, Worker worker) {
        super(company.getStreet(),type,use,company.getName()
                ,company.getNIF(),worker.getName(),worker.getNIF()
                ,worker.getSalary(), BillSettings.getConcept(Payroll.class.getSimpleName()));
        this.worker = worker;
        this.company = company;
    }

    public Worker getWorker() {
        return worker;
    }

    public ComplexCompany getCompany() {
        return company;
    }

    @Override
    public String getMessage() {
        return "The " + this.getWorker().getJob() + ":"
                + this.getReceiverName() + " has received the payroll from "
                + this.getIssuerName() +", amount: " +this.getTotal();
    }
}
