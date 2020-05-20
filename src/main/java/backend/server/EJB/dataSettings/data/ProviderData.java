package backend.server.EJB.dataSettings.data;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;

import java.util.Map;

public class ProviderData {

    private double initialSocialCapital;
    private Map<Product, Integer> productSalePriceTable;
    private double priceChange;
    private int closeLimit;

    public ProviderData(double initialSocialCapital, Map<Product, Integer> productSalePriceTable, double priceChange, int closeLimit) {
        this.initialSocialCapital = initialSocialCapital;
        this.productSalePriceTable = productSalePriceTable;
        this.priceChange = priceChange;
        this.closeLimit = closeLimit;
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public Map<Product, Integer> getProductSalePriceTable() {
        return productSalePriceTable;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public int getCloseLimit() {
        return closeLimit;
    }
}
