package backend.server.EJB.dataSettings.data;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;

import java.util.Map;

public class ProviderData {

    private double initialSocialCapital;
    private Map<Product, Double> productSalePriceTable;
    private double priceChange;
    private double closeLimit;

    public ProviderData(double initialSocialCapital, Map<Product, Double> productSalePriceTable, double priceChange, double closeLimit) {
        this.initialSocialCapital = initialSocialCapital;
        this.productSalePriceTable = productSalePriceTable;
        this.priceChange = priceChange;
        this.closeLimit = closeLimit;
    }

    public ProviderData(double initialSocialCapital, double priceChange, double closeLimit) {
        this.initialSocialCapital = initialSocialCapital;
        this.priceChange = priceChange;
        this.closeLimit = closeLimit;
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public Map<Product, Double> getProductSalePriceTable() {
        return productSalePriceTable;
    }

    public void setProductSalePriceTable(Map<Product, Double> productSalePriceTable) {
        this.productSalePriceTable = productSalePriceTable;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public double getCloseLimit() {
        return closeLimit;
    }
}
