package backend.implementations.loaders.providing.product;

import backend.model.simulables.provider.Product;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.view.loaders.provider.product.ProductPriceInitializer;

public class DistributionProductPriceInitializer implements ProductPriceInitializer {
    @Override
    public double getPrice(Product product) {
        return ProviderSettings.getProductCost(product);
    }
}
