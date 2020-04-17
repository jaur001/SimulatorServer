package backend.model.simulables.company.restaurant.administration;

import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulation.administration.Simulation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProviderSearcher {

    private Administrator administrator;
    private ComplexCompany company;

    public ProviderSearcher(Administrator administrator, ComplexCompany company) {
        this.administrator = administrator;
        this.company = company;
    }

    public void searchBetterOptions(){
        Arrays.stream(Product.values())
                .map(product -> company.searchProvider(product))
                .filter(Objects::nonNull)
                .forEach(this::changeProvider);
    }

    private void changeProvider(Provider provider) {
        Provider actualProvider = company.getProvider(provider.getProduct());
        if(actualProvider!=null) administrator.removeProvider(actualProvider);
        administrator.addProvider(provider);
    }

    public void addMissingProvider() {
        List<Product> products = getMissingProducts();
        products.forEach(product -> administrator.addProvider(company.searchProvider(product)));
    }

    private List<Product> getMissingProducts() {
        return Arrays.stream(Product.values())
                .filter(product -> !company.hasThisProvider(product))
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
}
