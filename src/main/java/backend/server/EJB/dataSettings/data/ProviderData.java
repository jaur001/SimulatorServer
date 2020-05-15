package backend.server.EJB.dataSettings.data;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;

import java.util.Map;

public class ProviderData {

    private double initialSocialCapital;
    private Map<Product, Integer> productCostTable;

    public ProviderData(double initialSocialCapital, Map<Product, Integer> productCostTable) {
        this.initialSocialCapital = initialSocialCapital;
        this.productCostTable = productCostTable;
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public Map<Product, Integer> getProductCostTable() {
        return productCostTable;
    }

    public void setInitialSocialCapital(double initialSocialCapital) {
        this.initialSocialCapital = initialSocialCapital;
    }

    public void setProductCostTable(Map<Product, Integer> productCostTable) {
        this.productCostTable = productCostTable;
    }
}
