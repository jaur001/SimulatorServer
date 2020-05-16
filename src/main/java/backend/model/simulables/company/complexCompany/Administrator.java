package backend.model.simulables.company.complexCompany;

import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.transactions.ProductRefundTransaction;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.administration.Simulator;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.utils.MathUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Administrator {

    protected List<Provider> providersList;
    protected FinancialData financialData;
    protected ComplexCompany company;

    public Administrator(FinancialData financialData, ComplexCompany company) {
        this.company = company;
        this.providersList = new CopyOnWriteArrayList<>();
        this.financialData = financialData;
    }

    public void addProvider(Provider provider){
        Simulator.addSimulableForCompany(company,provider);
    }

    public void removeProvider(Provider provider){
        Simulator.removeSimulableForCompany(company,provider);
    }

    public void addService(ServiceCompany serviceCompany){
        Simulator.addSimulableForCompany(company,serviceCompany);
    }

    public void removeService(ServiceCompany serviceCompany){
        Simulator.removeSimulableForCompany(company,serviceCompany);
    }

    public List<Provider> getProvidersList() {
        return providersList;
    }

    public boolean manageFinances() {
        return financialData.getTreasury() <= -5000;
    }

    public void checkProducts() {
        if(providersList.size()==0) return;
        if(!ProviderSettings.isBadProduct()) return;
        Provider provider = providersList.get(MathUtils.random(0,providersList.size()));
        Bank.makeTransaction(new ProductRefundTransaction(provider,company,provider.getPrice()/30));
    }

    public ComplexCompany getCompany() {
        return company;
    }
}
