package backend.view.loaders.secondaryCompanies.provider;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Product;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;

public interface ProvidingInitializer {
    Provider provide(Product product);
}
