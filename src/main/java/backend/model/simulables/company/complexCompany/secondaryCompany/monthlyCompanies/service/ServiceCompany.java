package backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service;

import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.MonthlyCompany;
import backend.model.simulation.settings.settingsList.ServiceSettings;
import backend.model.simulation.timeLine.TimeLine;

public class ServiceCompany extends MonthlyCompany<Service> {


    public ServiceCompany(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(NIF, companyName, creationDate, ownerName, street, telephoneNumber);
    }

    @Override
    public void setInitialPrice() {
        this.price = ServiceSettings.getPrice(product);
    }

    @Override
    public void simulate() {
        if(TimeLine.isLastDay()) {
            checkFinances();
        }
    }

    @Override
    protected FinancialData getInitialFinancialData() {
        return new FinancialData(ServiceSettings.getInitialSocialCapital());
    }

    @Override
    protected boolean manageFinances() {
        return financialData.getTreasury() <= ServiceSettings.getCloseLimit();
    }

    @Override
    protected void increasePrice() {
        price*= (1+ ServiceSettings.getPriceChange());
    }

    @Override
    protected void decreasePrice() {
        price*= (1-ServiceSettings.getPriceChange());
    }

    @Override
    protected void searchBetterProviders() {

    }

    @Override
    protected void searchBetterNeededServices() {

    }
}
