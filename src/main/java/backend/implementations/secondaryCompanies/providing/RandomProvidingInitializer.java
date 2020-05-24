package backend.implementations.secondaryCompanies.providing;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.utils.MathUtils;
import backend.view.loaders.secondaryCompanies.provider.ProvidingInitializer;

import java.util.List;

public class RandomProvidingInitializer implements ProvidingInitializer {
    @Override
    public Provider provide(Product product) {
        List<Provider> providersWithTheProduct = Simulation.getProviderList(product);
        if(providersWithTheProduct.size() == 0) return null;
        return providersWithTheProduct.get(MathUtils.random(0,providersWithTheProduct.size()));
    }
}
