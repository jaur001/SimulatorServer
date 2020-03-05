package backend.view.loaders.provider.product;

import backend.model.provider.Product;

public interface ProductPriceInitializer {
    double getPrice(Product product);
}
