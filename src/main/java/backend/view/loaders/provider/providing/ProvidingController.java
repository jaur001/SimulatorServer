package backend.view.loaders.provider.providing;

import backend.model.provider.Product;
import backend.model.provider.Provider;

import java.util.List;

public interface ProvidingController {
    Provider provide(List<Provider> providerList, Product product);
}
