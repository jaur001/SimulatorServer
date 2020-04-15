package backend.view.loaders.secondaryCompanies.provider;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;

public interface ProvidingInitializer {
    Provider provide(Product product);
}
