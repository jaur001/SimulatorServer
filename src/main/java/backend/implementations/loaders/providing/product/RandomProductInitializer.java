package backend.implementations.loaders.providing.product;

import backend.model.simulables.provider.Product;
import backend.utils.MathUtils;
import backend.view.loaders.provider.product.ProductInitializer;

public class RandomProductInitializer implements ProductInitializer {
    @Override
    public Product init() {
        return Product.values()[MathUtils.random(0,Product.values().length)];
    }
}
