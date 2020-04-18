package backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider;

import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.MonthlyCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.timeLine.TimeLine;

import java.util.ArrayList;

public class Provider extends MonthlyCompany<Product> {

    public Provider(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(NIF, companyName, creationDate, ownerName, street,telephoneNumber);
        companyList = new ArrayList<>();
        financialData.addDebt(getMortgage());
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
        if(hasNotThisService(Service.Transport)) searchAndAddService(Service.Transport);
        if(TimeLine.isLastDay()) {
            checkFinances();
        }
    }


    @Override
    protected void checkBetterProviders() {

    }

    @Override
    protected void checkBetterServices() {
        checkBetterServices(Service.Transport);
    }

}
