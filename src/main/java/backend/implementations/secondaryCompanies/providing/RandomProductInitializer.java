package backend.implementations.secondaryCompanies.providing;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Product;
import backend.utils.MathUtils;
import backend.view.loaders.secondaryCompanies.provider.ProductInitializer;

public class RandomProductInitializer implements ProductInitializer {
    @Override
    public Product init() {
        return Product.values()[MathUtils.random(0,Product.values().length)];
    }
}
