package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.settings.settingsData.data.ServiceData;
import backend.utils.MathUtils;

public class ServiceSettings {

    private static double serviceProbability = 1.0;

    private static ServiceData getServiceDataSettings() {
        return SimulationDataController.getServiceData();
    }

    public static double getInitialSocialCapital() {
        return getServiceDataSettings().getInitialSocialCapital();
    }

    public static double getPrice(Service service){
        return getServiceDataSettings().getServicePriceTable().get(service);
    }

    public static double getPriceChange() {
        return getServiceDataSettings().getPriceChange();
    }

    public static double getCloseLimit(){
        return getServiceDataSettings().getCloseLimit();
    }

    public static boolean newService() {
        return MathUtils.calculateProbability((int)((100 -  getServiceCompanyPercentage())*getServiceProbability()));
    }

    private static double getServiceCompanyPercentage() {
        return ((double)(Simulation.getServiceCompanySize()*5)/(double)(1+Simulation.getCompanyListCopy().size()))*100.0;
    }

    public static void setServiceProbability(double serviceProbability) {
        ServiceSettings.serviceProbability = serviceProbability;
    }

    public static double getServiceProbability() {
        return serviceProbability;
    }
}
