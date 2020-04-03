package backend.view.loaders.provider.product;

import backend.model.simulables.company.provider.Product;

public interface ProductPriceInitializer {
    double getPrice(Product product);
}
