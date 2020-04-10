package backend.model.simulables.company.restaurant.administration;

import backend.model.simulables.company.provider.Product;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulation.Simulation;

import java.util.Arrays;
import java.util.List;

public class ProviderSearcher {

    private Administrator administrator;

    public ProviderSearcher(Administrator administrator) {
        this.administrator = administrator;
    }

    public void searchBetterOptions(){
        Arrays.stream(Product.values())
                .map(this::getBestOption)
                .filter(provider -> !administrator.getProvidersList().contains(provider))
                .forEach(this::changeProvider);
    }

    private Provider getBestOption(Product product) {
        List<Provider> providerList = Simulation.getProviderList(product);
        if(providerList.size()==0) return administrator.getProvider(product);
        return providerList.stream()
                .reduce(providerList.get(0),this::getBetterProvider);
    }

    private Provider getBetterProvider(Provider provider1, Provider provider2) {
        return provider1.getProductPrice()<=provider2.getProductPrice()? provider1 : provider2;
    }

    private void changeProvider(Provider provider) {
        administrator.removeProvider(getActualProvider(provider));
        administrator.addProvider(provider);
    }

    private Provider getActualProvider(Provider provider) {
        return administrator.getProvider(provider.getProduct());
    }
}
