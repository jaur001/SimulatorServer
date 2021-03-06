package backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider;

import backend.model.NIFCreator.SecondaryNIFCreator;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.MonthlyCompany;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.timeLine.TimeLine;

public class Provider extends MonthlyCompany<Product> {

    public Provider(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(NIF, companyName, creationDate, ownerName, street,telephoneNumber);
    }

    public Provider(String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        this(new SecondaryNIFCreator().create(),companyName, creationDate, ownerName, street, telephoneNumber);
    }

    @Override
    public void setInitialPrice() {
        setPrice(ProviderSettings.getProductCost(product));
    }

    @Override
    protected FinancialData getInitialFinancialData() {
        return new FinancialData(ProviderSettings.getInitialSocialCapital());
    }

    @Override
    protected boolean manageFinances() {
        return financialData.getTreasury() <= ProviderSettings.getCloseLimit();
    }

    @Override
    public void simulate() {
        if(hasNotThisService(Service.Transport)) searcher.searchAndAddService(Service.Transport);
        if(TimeLine.isLastDay()) {
            checkFinances();
        }
    }

    @Override
    protected void increasePrice() {
        price*= (1+ ProviderSettings.getPriceChange());
    }

    @Override
    protected void decreasePrice() {
        price*= (1-ProviderSettings.getPriceChange());
    }

    @Override
    protected void searchBetterProviders() {

    }

    @Override
    protected void searchBetterNeededServices() {
        searcher.searchBetterService(Service.Transport);
    }

}
