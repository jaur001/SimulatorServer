package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.SimulationDataController;
import backend.server.EJB.dataSettings.data.ProviderData;
import backend.utils.MathUtils;

public class ProviderSettings{


    private static ProviderData getProviderDataSettings() {
        return SimulationDataController.getProviderSessionData();
    }

    public static double getInitialSocialCapital() {
        return getProviderDataSettings().getInitialSocialCapital();
    }

    public static double getProductCost(Product product){
        return getProviderDataSettings().getProductSalePriceTable().get(product);
    }

    public static boolean isBadProduct(){
        return MathUtils.random(0,1000)<1;
    }

    public static boolean newProvider() {
        return MathUtils.calculateProbability((int)((100-getProviderRestaurantPercentage())*getProviderProbability()));
    }

    private static double getProviderRestaurantPercentage(){
        return ((double)Simulation.getProviderSize()/(double)(1+Simulation.getRestaurantSize()))*100.0;
    }

    private static double getProviderProbability() {
        return 1;
    }

    public static double getCloseLimit() {
        return getProviderDataSettings().getCloseLimit();
    }

    public static double getPriceChange(){
        return getProviderDataSettings().getPriceChange();
    }
}
