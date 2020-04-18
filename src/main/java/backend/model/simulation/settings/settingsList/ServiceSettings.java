package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulation.administration.Simulation;
import backend.utils.MathUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ServiceSettings {

    private static Map<Service,Double> priceTable = new LinkedHashMap<>();

    static {
        Double[] prices = new Double[]{100.0,100.0};
        IntStream.range(0,Service.values().length).boxed()
                .forEach(i -> priceTable.put(Service.values()[i],prices[i]));
    }

    public static double getPrice(Service service){
        return priceTable.get(service);
    }

    public static boolean newService() {
        return MathUtils.calculateProbability(100 - (int) getServiceCompanyPercentage());
    }

    private static double getServiceCompanyPercentage() {
        return ((double)(Simulation.getServiceCompanySize()*5)/(double)(1+Simulation.getRestaurantSize()+Simulation.getProviderSize()))*100.0;
    }
}
