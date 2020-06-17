package backend.model.simulables.company.complexCompany;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Product;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.administration.centralControl.Simulation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompaniesSearcher {

    private Administrator administrator;

    public CompaniesSearcher(Administrator administrator) {
        this.administrator = administrator;
    }

    public void searchBetterProviders(){
        Arrays.stream(Product.values())
                .map(this::searchProvider)
                .filter(Objects::nonNull)
                .forEach(this::changeProvider);
    }

    private void changeProvider(Provider provider) {
        Provider actualProvider = administrator.getCompany().getProvider(provider.getProduct());
        if(actualProvider!=null) administrator.removeProvider(actualProvider);
        administrator.addProvider(provider);
    }

    public void addMissingProvider() {
        List<Product> products = getMissingProducts();
        products.forEach(product -> administrator.addProvider(searchProvider(product)));
    }

    private List<Product> getMissingProducts() {
        return Arrays.stream(Product.values())
                .filter(product -> !administrator.getCompany().hasThisProvider(product))
                .collect(Collectors.toList());
    }

    public void removeUnnecessaryProviders() {
        List<Provider> providerList = Arrays.stream(Product.values())
                .flatMap(product -> administrator.getProvidersList().stream()
                        .filter(Objects::nonNull)
                            .filter(provider -> provider.getProduct().equals(product))
                            .limit(1))
                .collect(Collectors.toCollection(LinkedList::new));
        removeUnnecessaryProviders(providerList);
    }

    private void removeUnnecessaryProviders(List<Provider> providersNeeded) {
        administrator.getProvidersList().stream()
                .filter(provider -> !providersNeeded.contains(provider))
                .forEach(provider -> administrator.removeProvider(provider));
    }

    public Provider searchProvider(Product product) {
        List<Provider> options = Simulation.getProviderList(product);
        if(options.size() == 0) return null;
        return options.stream()
                .reduce(options.get(0), CompaniesSearcher::getBetterProvider);
    }

    public static Provider getBetterProvider(Provider serviceCompany1, Provider serviceCompany2) {
        return serviceCompany1.getPrice() <= serviceCompany2.getPrice()? serviceCompany1 : serviceCompany2;
    }

    public ServiceCompany searchService(Service service) {
        List<ServiceCompany> options = Simulation.getServiceCompanyList(service);
        if(options.size() == 0) return null;
        return options.stream()
                .reduce(options.get(0), CompaniesSearcher::getBetterService);
    }

    public static ServiceCompany getBetterService(ServiceCompany serviceCompany1, ServiceCompany serviceCompany2) {
        return serviceCompany1.getPrice() <= serviceCompany2.getPrice()? serviceCompany1 : serviceCompany2;
    }

    public void searchAndAddService(Service service) {
        ServiceCompany simulable = searchService(service);
        if(simulable!=null) administrator.getCompany().addService(simulable);
    }

    public void searchBetterService(Service service) {
        if(administrator.getCompany().getService(service)==null)return;
        ServiceCompany serviceCompany = searchService(service);
        if(serviceCompany == null) return;
        if(serviceCompany.getPrice()<administrator.getCompany().getService(service).getPrice())changeService(serviceCompany);
    }

    public void changeService(ServiceCompany company) {
        administrator.removeService(administrator.getCompany().getService(Service.Transport));
        administrator.addService(company);
    }
}
