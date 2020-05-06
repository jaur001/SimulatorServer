package backend.model.simulation.settings.settingsList;

import backend.model.bill.bills.*;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.server.EJB.dataSettings.dataSettingsEJB.BillSettingsStatefulBean;
import backend.utils.MathUtils;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.HashMap;
import java.util.Map;

public class BillSettings{

    private static final Map<String,String> conceptsTable = new HashMap<>();
    private static BillSettingsStatefulBean billDataSettings;


    static {
        conceptsTable.put(EatingBill.class.getSimpleName(),"Bill of a eating.");
        conceptsTable.put(ProductPurchase.class.getSimpleName(),"Purchase of a product for the restaurant.");
        conceptsTable.put(ProductRefund.class.getSimpleName(),"Refund of a product in bad conditions.");
        conceptsTable.put(Payroll.class.getSimpleName(),"Payroll o a worker.");
        conceptsTable.put(ServiceBill.class.getSimpleName(),"Service for company.");
        conceptsTable.put(BuildingInversion.class.getSimpleName(),"Mortgage for company.");
    }

    public static void init(BillSettingsStatefulBean dataSettings) {
        billDataSettings = dataSettings;
    }


    public static String getConcept(String billType){
        return conceptsTable.get(billType);
    }

    public static double calculatePrice(Restaurant restaurant, int peopleInvited){
        double mean = MathUtils.twoNumberMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return getPriceOfPlate(restaurant, mean) * getPlateNumberSample() * (peopleInvited+1);

    }

    public static double getPlateNumberMean(){
        return new NormalDistribution(billDataSettings.getPlateNumberMean(),billDataSettings.getPlateNumberSD()).getMean();
    }

    private static int getPlateNumberSample() {
        double sample = Math.round(Math.abs(new NormalDistribution(billDataSettings.getPlateNumberMean(),billDataSettings.getPlateNumberSD()).sample()));
        return (int)(sample<1? 1: sample);
    }

    private static double getPriceOfPlate(Restaurant restaurant, double mean) {
        return platePriceIsConstant(restaurant) ? mean :
                getPlatePriceSample(restaurant);
    }

    private static boolean platePriceIsConstant(Restaurant restaurant) {
        return restaurant.getMaxPricePlate()==restaurant.getMinPricePlate();
    }

    private static double getPlatePriceSample(Restaurant restaurant){
        double mean = MathUtils.twoNumberMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return Math.abs(new NormalDistribution(mean,restaurant.getMaxPricePlate()-mean).sample());
    }

}
