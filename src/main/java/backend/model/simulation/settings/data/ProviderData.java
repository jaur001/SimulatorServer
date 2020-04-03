package backend.model.simulation.settings.data;

import backend.model.simulables.company.provider.Product;

import java.util.Map;

public class ProviderData {
    private Map<Product, Integer> productCostTable;
    public double initialSocialCapital;

    public ProviderData(Map<Product, Integer> productCostTable, double initialSocialCapital) {
        this.productCostTable = productCostTable;
        this.initialSocialCapital = initialSocialCapital;
    }

    public Map<Product, Integer> getProductCostTable() {
        return productCostTable;
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }
}
