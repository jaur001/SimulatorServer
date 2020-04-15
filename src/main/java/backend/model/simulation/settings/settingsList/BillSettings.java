package backend.model.simulation.settings.settingsList;

import backend.model.bill.bills.*;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulation.settings.Adjustable;
import backend.model.simulation.settings.SettingsData;
import backend.model.simulation.settings.data.BillData;
import backend.utils.MathUtils;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.HashMap;
import java.util.Map;

public class BillSettings implements Adjustable {

    private static final int PLATE_NUMBER_MEAN = 2;
    private static final double PLATE_NUMBER_SD = 0.7;
    private static final Map<String,String> conceptsTable = new HashMap<>();

    private static NormalDistribution plateNumberDistribution;

    static {
        getDefaultSettings();
        conceptsTable.put(EatingBill.class.getSimpleName(),"Bill of a eating.");
        conceptsTable.put(ProductPurchase.class.getSimpleName(),"Purchase of a product for the restaurant.");
        conceptsTable.put(ProductRefund.class.getSimpleName(),"Refund of a product in bad conditions.");
        conceptsTable.put(Payroll.class.getSimpleName(),"Payroll o a worker.");
        conceptsTable.put(ServiceBill.class.getSimpleName(),"Service for company.");
    }

    private static void getDefaultSettings() {
        plateNumberDistribution = new NormalDistribution(PLATE_NUMBER_MEAN,PLATE_NUMBER_SD);
    }
    @Override
    public void init(SettingsData data) {
        BillData billData = data.getBillData();
        plateNumberDistribution = new NormalDistribution(billData.getPlateNumberMean(), billData.getPlateNumberSd());
    }

    @Override
    public void setDefault() {
        getDefaultSettings();
    }

    public static double getPlateNumberMean(){
        return plateNumberDistribution.getMean();
    }


    public static String getConcept(String billType){
        return conceptsTable.get(billType);
    }

    public static double calculatePrice(Restaurant restaurant, int peopleInvited){
        double mean = MathUtils.twoNumberMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return getPriceOfPlate(restaurant, mean) * getPlateNumberSample() * (peopleInvited+1);

    }

    private static int getPlateNumberSample() {
        double sample = Math.round(Math.abs(plateNumberDistribution.sample()));
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
