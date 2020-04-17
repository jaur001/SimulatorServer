package backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider;

import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.transactions.ServiceBillTransaction;
import backend.model.simulables.company.secondaryCompany.SecondaryCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.MonthlyCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.Simulator;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.timeLine.TimeLine;

import java.util.ArrayList;
import java.util.List;

public class Provider extends MonthlyCompany<Product> {

    public Provider(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(NIF, companyName, creationDate, ownerName, street,telephoneNumber);
        companyList = new ArrayList<>();
        financialData.addDebt(getTaxes());
    }

    public Provider(String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        this(new ProviderNIFCreator().create(),companyName, creationDate, ownerName, street, telephoneNumber);
    }

    @Override
    public void setPrice() {
        setPrice(ProviderSettings.getProductCost(product));
    }


    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        if(!hasThisService(Service.Transport)) addService();
        if(TimeLine.isLastDay()) {
            checkFinances();
        }
    }

    public void addService() {
        ServiceCompany simulable = searchService(Service.Transport);
        if(simulable!=null)Simulator.addSimulableForCompany(this, simulable);
    }


}
