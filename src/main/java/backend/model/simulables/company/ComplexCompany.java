package backend.model.simulables.company;

import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.transactions.PayrollTransaction;
import backend.model.simulables.bank.transactions.ProductPurchaseTransaction;
import backend.model.simulables.bank.transactions.ServiceBillTransaction;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.Simulator;

import java.util.LinkedList;
import java.util.List;

public abstract class ComplexCompany extends Company{

    protected List<ServiceCompany> services;
    protected List<Provider> providers;
    protected List<Worker> workers;

    public ComplexCompany(int NIF, String companyName, String street, String telephoneNumber, FinancialData financialData) {
        super(NIF, companyName, street, telephoneNumber, financialData);
        services = new LinkedList<>();
        providers = new LinkedList<>();
        workers = new LinkedList<>();
    }


    public void addService(ServiceCompany serviceCompany) {
        services.add(serviceCompany);
        financialData.addDebt(serviceCompany,serviceCompany.getPrice());
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public List<ServiceCompany> getServices() {
        return services;
    }

    public List<Worker> getWorkers() {
        return workers;
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

    public ServiceCompany searchService(Service service) {
        List<ServiceCompany> options = Simulation.getServiceCompanyList(service);
        if(options.size() == 0) return null;
        return options.stream()
                .reduce(options.get(0), ComplexCompany::getBetterService);
    }

    public static ServiceCompany getBetterService(ServiceCompany serviceCompany1, ServiceCompany serviceCompany2) {
        return serviceCompany1.getPrice() <= serviceCompany2.getPrice()? serviceCompany1 : serviceCompany2;
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

    public Provider searchProvider(Product product) {
        List<Provider> options = Simulation.getProviderList(product);
        if(options.size() == 0) return null;
        return options.stream()
                .reduce(options.get(0), ComplexCompany::getBetterProvider);
    }

    public static Provider getBetterProvider(Provider serviceCompany1, Provider serviceCompany2) {
        return serviceCompany1.getPrice() <= serviceCompany2.getPrice()? serviceCompany1 : serviceCompany2;
    }

    public void addWorker(Worker worker) {
        worker.hire(this,worker.getSalaryDesired());
        workers.add(worker);
        financialData.addDebt(worker,worker.getSalary());
    }

    public void removeWorker(Worker worker) {
        if(workers.contains(worker)){
            worker.fire();
            workers.remove(worker);
            financialData.removeDebt(worker);
        }
    }

    public void retireWorker(Worker worker) {
        if(workers.contains(worker)){
            worker.retire();
            workers.remove(worker);
            financialData.removeDebt(worker);
        }
    }

    @Override
    protected void checkFinances() {
        payCompanyDebts();
        financialData.reset();
        changePrice();
        checkBetterProviders();
        checkBetterServices();
        analyzeFinances();
    }

    protected void payCompanyDebts() {
        payProvider();
        payServices();
        payWorkers();
    }

    protected abstract void checkBetterProviders();

    protected abstract void checkBetterServices();

    protected void searchAndAddService(Service service) {
        ServiceCompany simulable = searchService(service);
        if(simulable!=null)Simulator.addSimulableForCompany(this, simulable);
    }

    protected void checkBetterServices(Service service) {
        if(this.getService(service)==null) searchAndAddService(service);
        ServiceCompany serviceCompany = searchService(service);
        if(serviceCompany == null) return;
        if(serviceCompany.getPrice()<this.getService(service).getPrice())changeService(serviceCompany);
    }

    public void changeService(ServiceCompany company) {
        Simulator.removeSimulableForCompany(this,this.getService(Service.Transport));
        Simulator.addSimulableForCompany(this,company);
    }

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
