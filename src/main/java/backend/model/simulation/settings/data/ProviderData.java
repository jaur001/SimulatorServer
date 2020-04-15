package backend.model.simulation.settings.data;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;

import java.util.Map;

public class ProviderData {
    public double initialSocialCapital;

    public ProviderData(Map<Product, Integer> productCostTable, double initialSocialCapital) {
        this.initialSocialCapital = initialSocialCapital;
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }
}
