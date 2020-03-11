package backend.implementations.loaders.provider.product;

import backend.model.provider.Product;
import backend.model.utils.ProviderUtils;
import backend.view.loaders.provider.product.ProductPriceInitializer;

public class DistributionProductPriceInitializer implements ProductPriceInitializer {
    @Override
    public double getPrice(Product product) {
        return ProviderUtils.getProductCost(product);
    }
}
