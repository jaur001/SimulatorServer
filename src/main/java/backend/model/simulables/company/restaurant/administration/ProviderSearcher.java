package backend.model.simulables.company.restaurant.administration;

import backend.model.simulables.company.provider.Product;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulation.Simulation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProviderSearcher {

    private Administrator administrator;

    public ProviderSearcher(Administrator administrator) {
        this.administrator = administrator;
    }

    public void searchBetterOptions(){
        Arrays.stream(Product.values())
                .map(this::getBestOption)
                .filter(Objects::nonNull)
                .forEach(this::changeProvider);
    }

    private Provider getBestOption(Product product) {
        List<Provider> providerList = Simulation.getProviderList(product);
        if(providerList.size()==0) return null;
        return providerList.stream()
                .reduce(providerList.get(0),this::getBetterProvider);
    }

    private Provider getBetterProvider(Provider provider1, Provider provider2) {
        return provider1.getProductPrice()<=provider2.getProductPrice()? provider1 : provider2;
    }

    private void changeProvider(Provider provider) {
        Provider actualProvider = administrator.getProvider(provider.getProduct());
        if(actualProvider!=null) administrator.removeProvider(actualProvider);
        administrator.addProvider(provider);
    }

    public void addMissingProvider() {
        List<Product> products = getMissingProvider();
        products.forEach(product -> administrator.addProvider(getBestOption(product)));
    }

    private List<Product> getMissingProvider() {
        return Arrays.stream(Product.values())
                .filter(product -> administrator.getProvidersList().stream()
                    .noneMatch(provider -> provider.getProduct().equals(product)))
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

    private void removeUnnecessaryProviders(List<Provider> providerList) {
        administrator.getProvidersList().stream()
                .filter(provider -> !providerList.contains(provider))
                .forEach(provider -> administrator.removeProvider(provider));
    }
}
