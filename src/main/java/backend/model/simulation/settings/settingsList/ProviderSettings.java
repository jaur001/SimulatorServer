package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.secondaryCompany.monthlyCompanies.provider.Product;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.server.EJB.dataSettings.data.ProviderData;
import backend.utils.MathUtils;

public class ProviderSettings{

    private static double providerProbability = 1.0;

    private static ProviderData getProviderDataSettings() {
        return SimulationDataController.getProviderData();
    }

    public static double getInitialSocialCapital() {
        return getProviderDataSettings().getInitialSocialCapital();
    }

    public static double getProductCost(Product product){
        return getProviderDataSettings().getProductSalePriceTable().get(product);
    }

    public static boolean isBadProduct(){
        return MathUtils.random(0,1000)<2;
    }

    public static boolean newProvider() {
        return MathUtils.calculateProbability((int)((100-getProviderRestaurantPercentage())*getProviderProbability()));
    }

    private static double getProviderRestaurantPercentage(){
        return ((double)Simulation.getProviderSize()/(double)(1+Simulation.getRestaurantSize()))*100.0;
    }

    public static double getCloseLimit() {
        return getProviderDataSettings().getCloseLimit();
    }

    public static double getPriceChange(){
        return getProviderDataSettings().getPriceChange();
    }

    public static void setProviderProbability(double providerProbability) {
        ProviderSettings.providerProbability = providerProbability;
    }

    public static double getProviderProbability() {
        return providerProbability;
    }
}
