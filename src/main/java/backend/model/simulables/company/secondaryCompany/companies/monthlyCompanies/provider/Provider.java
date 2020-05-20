package backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider;

import backend.model.NIFCreator.SecondaryNIFCreator;
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
        this(new SecondaryNIFCreator().create(),companyName, creationDate, ownerName, street, telephoneNumber);
    }

    @Override
    public void setPrice() {
        setPrice(ProviderSettings.getProductCost(product));
    }

    @Override
    protected boolean manageFinances() {
        return financialData.getTreasury() <= -ProviderSettings.getCloseLimit();
    }

    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        if(hasNotThisService(Service.Transport)) searcher.searchAndAddService(Service.Transport);
        if(TimeLine.isLastDay()) {
            checkFinances();
        }
    }


    @Override
    protected void searchBetterProviders() {

    }

    @Override
    protected void searchBetterNeededServices() {
        searcher.searchBetterService(Service.Transport);
    }

}
