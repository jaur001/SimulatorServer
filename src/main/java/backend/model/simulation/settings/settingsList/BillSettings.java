package backend.model.simulation.settings.settingsList;

import backend.model.bill.bills.*;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.settings.settingsData.data.ClientData;
import backend.utils.MathUtils;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.HashMap;
import java.util.Map;

public class BillSettings{

    private static final Map<String,String> conceptsTable = new HashMap<>();


    static {
        conceptsTable.put(EatingBill.class.getSimpleName(),"Bill of a eating.");
        conceptsTable.put(ProductPurchase.class.getSimpleName(),"Purchase of a product for the restaurant.");
        conceptsTable.put(ProductRefund.class.getSimpleName(),"Refund of a product in bad conditions.");
        conceptsTable.put(Payroll.class.getSimpleName(),"Payroll o a worker.");
        conceptsTable.put(ServiceBill.class.getSimpleName(),"Service for company.");
        conceptsTable.put(BuildingInversion.class.getSimpleName(),"Mortgage for company.");
    }

    private static ClientData getClientDataSettings() {
        return SimulationDataController.getClientData();
    }



    public static String getConcept(String billType){
        return conceptsTable.get(billType);
    }

    public static double calculatePrice(Restaurant restaurant, int peopleInvited){
        double mean = MathUtils.twoNumberMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return getPriceOfPlate(restaurant, mean) * getPlateNumberSample() * (peopleInvited+1);

    }

    public static double getPlateNumberMean(){
        return getNormalDistribution().getMean();
    }

    public static NormalDistribution getNormalDistribution() {
        return getClientDataSettings().getPlateNumberDistribution();
    }

    private static int getPlateNumberSample() {
        double sample = Math.round(Math.abs(getNormalDistribution().sample()));
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
