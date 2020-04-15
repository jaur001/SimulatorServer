package backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider;

import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.transactions.ServiceBillTransaction;
import backend.model.simulables.company.secondaryCompany.SecondaryCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.MonthlyCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.timeLine.TimeLine;

import java.util.ArrayList;

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
        if(!hasThisService(Service.Transport))searchTransportService();
        if(TimeLine.isLastDay()) {
            payTransport();
            checkFinances();
        }
    }

    private void payTransport() {
        ServiceCompany company = getService(Service.Transport);
        if(company != null) Bank.makeTransaction(new ServiceBillTransaction(company,this,company.getPrice()));
    }

    private void searchTransportService() {

    }
}
