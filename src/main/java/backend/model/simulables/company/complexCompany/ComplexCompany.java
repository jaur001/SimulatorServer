package backend.model.simulables.company.complexCompany;

import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.transactions.PayrollTransaction;
import backend.model.simulables.bank.transactions.ProductPurchaseTransaction;
import backend.model.simulables.bank.transactions.ServiceBillTransaction;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.provider.Product;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.ServiceCompany;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class ComplexCompany extends Company {

    protected List<ServiceCompany> services;
    protected List<Provider> providers;
    protected CompaniesSearcher searcher;

    public ComplexCompany(int NIF, String companyName, String street, String telephoneNumber) {
        super(NIF, companyName, street, telephoneNumber);
        services = new CopyOnWriteArrayList<>();
        providers = new CopyOnWriteArrayList<>();
        this.searcher = new CompaniesSearcher(new Administrator(financialData,this));
    }


    public List<Provider> getProviders() {
        return providers;
    }

    public List<ServiceCompany> getServices() {
        return services;
    }

    public void addService(ServiceCompany serviceCompany) {
        services.add(serviceCompany);
        financialData.addDebt(serviceCompany,serviceCompany.getPrice());
    }

    public void removeService(ServiceCompany serviceCompany) {
        services.remove(serviceCompany);
        financialData.removeDebt(serviceCompany);
    }

    public boolean hasNotThisService(Service service){
        return services.stream()
                .noneMatch(serviceCompany -> serviceCompany.getProduct().equals(service));
    }

    public ServiceCompany getService(Service service){
        return services.stream()
                .filter(serviceCompany -> serviceCompany.getProduct().equals(service))
                .findFirst().orElse(null);
    }

    public void addProvider(Provider provider) {
        providers.add(provider);
        financialData.addDebt(provider,provider.getPrice());
    }

    public void removeProvider(Provider provider) {
        providers.remove(provider);
        financialData.removeDebt(provider);
    }

    public boolean hasThisProvider(Product product){
        return providers.stream()
                .anyMatch(provider -> provider.getProduct().equals(product));
    }

    public Provider getProvider(Product product){
        return providers.stream()
                .filter(provider -> provider.getProduct().equals(product))
                .findFirst().orElse(null);
    }

    @Override
    protected void checkFinances() {
        payCompanyDebts();
        financialData.reset();
        changePrice();
        searchBetterProviders();
        searchBetterNeededServices();
        analyzeFinances();
    }

    protected void payCompanyDebts() {
        payProvider();
        payServices();
        payWorkers();
    }

    protected abstract void searchBetterProviders();

    protected abstract void searchBetterNeededServices();

    private void payWorkers() {
        financialData.getPayrolls()
                .forEach((worker, salary) -> Bank.makeTransaction(new PayrollTransaction(this,worker,salary)));
    }

    protected void payServices() {
        financialData.getDebtsTable().keySet().stream()
                .filter(company -> company instanceof ServiceCompany)
                .map(company -> (ServiceCompany) company)
                .forEach(serviceCompany -> Bank.makeTransaction(new ServiceBillTransaction(serviceCompany,this,serviceCompany.getPrice())));
    }

    protected void payProvider() {
        financialData.getDebtsTable().keySet().stream()
            .filter(company -> company instanceof Provider)
            .map(company -> (Provider) company)
            .forEach(provider -> Bank.makeTransaction(new ProductPurchaseTransaction(provider,this,provider.getPrice())));
    }
}
