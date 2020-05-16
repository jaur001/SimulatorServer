package backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.MonthlyCompany;
import backend.model.simulation.settings.settingsList.ServiceSettings;
import backend.model.simulation.timeLine.TimeLine;

import java.util.LinkedList;

public class ServiceCompany extends MonthlyCompany<Service> {


    public ServiceCompany(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(NIF, companyName, creationDate, ownerName, street, telephoneNumber);
        this.product = null;
        this.price = 0;
        companyList = new LinkedList<>();
    }

    @Override
    public void setPrice() {
        this.price = ServiceSettings.getPrice(product);
    }

    @Override
    public void simulate() {
        if(TimeLine.isLastDay()) {
            checkFinances();
        }
    }

    @Override
    protected void searchBetterProviders() {

    }

    @Override
    protected void searchBetterNeededServices() {

    }
}
