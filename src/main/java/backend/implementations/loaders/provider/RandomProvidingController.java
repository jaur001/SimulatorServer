package backend.implementations.loaders.provider;

import backend.model.provider.Product;
import backend.model.provider.Provider;
import backend.model.utils.MathUtils;
import backend.view.loaders.provider.providing.ProvidingController;

import java.util.List;
import java.util.stream.Collectors;

public class RandomProvidingController implements ProvidingController {
    @Override
    public Provider provide(List<Provider> providerList, Product product) {
        List<Provider> providersWithTheProduct = providerList.stream()
                .filter(provider -> provider.getProduct().name().equals(product.name()))
                .collect(Collectors.toList());
        return providersWithTheProduct.get(MathUtils.random(0,providersWithTheProduct.size()));
    }
}
