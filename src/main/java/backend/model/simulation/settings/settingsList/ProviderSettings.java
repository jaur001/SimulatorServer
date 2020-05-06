package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulation.administration.Simulation;
import backend.server.EJB.dataSettings.dataSettingsEJB.ProviderSettingsStatefulBean;
import backend.utils.MathUtils;

public class ProviderSettings{

    public static final double PRICE_CHANGE = 0.01;
    private static ProviderSettingsStatefulBean providerDataSettings;

    public static void init(ProviderSettingsStatefulBean dataSettings) {
        providerDataSettings = dataSettings;
    }

    public static double getInitialSocialCapital() {
        return providerDataSettings.getInitialSocialCapital();
    }

    public static int getProductCost(Product product){
        return providerDataSettings.getProductCostTable().get(product);
    }

    public static boolean isBadProduct(){
        return MathUtils.random(0,1000)<1;
    }

    public static boolean newProvider() {
        return MathUtils.calculateProbability(100-(int)getProviderRestaurantPercentage());
    }

    private static double getProviderRestaurantPercentage(){
        return ((double)Simulation.getProviderSize()/(double)(1+Simulation.getRestaurantSize()))*100.0;
    }
}
