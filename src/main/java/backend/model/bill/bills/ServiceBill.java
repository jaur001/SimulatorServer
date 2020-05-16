package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.bill.Use;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.settings.settingsList.BillSettings;

public class ServiceBill extends CFDIBill {

    private static final Type type = Type.income;
    private static final Use use = Use.G03;
    private ServiceCompany serviceCompany;
    private ComplexCompany company;

    public ServiceBill(ServiceCompany serviceCompany, ComplexCompany company) {
        super(company.getStreet(), type, use, serviceCompany.getName()
                , serviceCompany.getNIF(), company.getName(), company.getNIF()
                , serviceCompany.getPrice(), BillSettings.getConcept(ServiceBill.class.getSimpleName()));
        this.company = company;
        this.serviceCompany = serviceCompany;
    }

    @Override
    public String getMessage() {
        return company.getName() + " has payed the " + serviceCompany.getProduct().toString() + " service to " + serviceCompany.getName() + ".";
    }
}
