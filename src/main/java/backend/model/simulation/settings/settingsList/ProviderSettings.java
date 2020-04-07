package backend.model.simulation.settings.settingsList;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.simulables.company.provider.Product;
import backend.model.simulation.settings.Adjustable;
import backend.model.simulation.settings.SettingsData;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ProviderSettings implements Adjustable {

    public static final double PRICE_CHANGE = 0.10;
    private static final int INITIAL_SOCIAL_CAPITAL = 10000;

    private static Map<Product, Integer> productCostTable = new LinkedHashMap<>();
    private static double initialSocialCapital;

    static {
        getDefaultSettings();
    }
    private static void getDefaultSettings() {
        initialSocialCapital = INITIAL_SOCIAL_CAPITAL;
        getDefaultProductCost();
    }


    private static void getDefaultProductCost() {
        Integer[] cost = {150,160,160,80,80,100,90};
        IntStream.range(0,cost.length).boxed()
                .forEach(i -> productCostTable.put(Product.values()[i],cost[i]));
    }

    @Override
    public void init(SettingsData data) {
        initialSocialCapital = data.getProviderData().getInitialSocialCapital();

    }

    @Override
    public void setDefault() {
        getDefaultSettings();
    }

    public static double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public static int getProductCost(Product product){
        return productCostTable.get(product);
    }

    public static int getLimit() throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().readCount("Provider");
    }
}
