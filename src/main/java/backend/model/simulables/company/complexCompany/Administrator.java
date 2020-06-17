package backend.model.simulables.company.complexCompany;

import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.transactions.ProductRefundTransaction;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.administration.centralControl.SimulationAdministrator;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.utils.MathUtils;

import java.util.List;

public class Administrator {

    protected FinancialData financialData;
    protected ComplexCompany company;

    public Administrator(FinancialData financialData, ComplexCompany company) {
        this.company = company;
        this.financialData = financialData;
    }

    public void addProvider(Provider provider){
        SimulationAdministrator.addSimulableForCompany(company,provider);
    }

    public void removeProvider(Provider provider){
        SimulationAdministrator.removeSimulableForCompany(company,provider);
    }

    public void addService(ServiceCompany serviceCompany){
        SimulationAdministrator.addSimulableForCompany(company,serviceCompany);
    }

    public void removeService(ServiceCompany serviceCompany){
        SimulationAdministrator.removeSimulableForCompany(company,serviceCompany);
    }

    public List<Provider> getProvidersList() {
        return company.getProviders();
    }

    public void checkProducts() {
        if(getProvidersList().size()==0) return;
        if(ProviderSettings.isBadProduct()) refundProduct();
    }

    public void refundProduct() {
        Provider provider = getProvidersList().get(MathUtils.random(0,getProvidersList().size()));
        Bank.makeTransaction(new ProductRefundTransaction(provider,company,provider.getPrice()/30));
    }

    public ComplexCompany getCompany() {
        return company;
    }
}
