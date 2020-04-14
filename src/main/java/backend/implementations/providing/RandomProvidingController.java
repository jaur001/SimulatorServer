package backend.implementations.providing;

import backend.model.simulables.company.provider.Product;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulation.administration.Simulation;
import backend.utils.MathUtils;
import backend.view.loaders.provider.providing.ProvidingController;

import java.util.List;
import java.util.stream.Collectors;

public class RandomProvidingController implements ProvidingController {
    @Override
    public Provider provide(Product product) {
        List<Provider> providersWithTheProduct = Simulation.getProviderListCopy().stream()
                .filter(provider -> provider.getProduct().name().equals(product.name()))
                .collect(Collectors.toList());
        if(providersWithTheProduct.size() == 0) return null;
        return providersWithTheProduct.get(MathUtils.random(0,providersWithTheProduct.size()));
    }
}
