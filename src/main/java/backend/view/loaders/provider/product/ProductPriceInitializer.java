package backend.view.loaders.provider.product;

import backend.model.simulables.provider.Product;

public interface ProductPriceInitializer {
    double getPrice(Product product);
}
