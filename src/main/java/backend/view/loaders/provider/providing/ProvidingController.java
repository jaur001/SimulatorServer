package backend.view.loaders.provider.providing;

import backend.model.simulables.company.provider.Product;
import backend.model.simulables.company.provider.Provider;

import java.util.List;

public interface ProvidingController {
    Provider provide(List<Provider> providerList, Product product);
}
