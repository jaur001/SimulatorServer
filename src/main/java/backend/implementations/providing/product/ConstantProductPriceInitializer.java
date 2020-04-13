package backend.implementations.providing.product;

import backend.model.simulables.company.provider.Product;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.view.loaders.provider.product.ProductPriceInitializer;

public class ConstantProductPriceInitializer implements ProductPriceInitializer {
    @Override
    public double getPrice(Product product) {
        return ProviderSettings.getProductCost(product);
    }
}
