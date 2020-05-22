package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.SimulationDataController;
import backend.server.EJB.dataSettings.data.ServiceData;
import backend.utils.MathUtils;

public class ServiceSettings {


    private static ServiceData getServiceDataSettings() {
        return SimulationDataController.getServiceSessionData();
    }

    public static double getInitialSocialCapital() {
        return getServiceDataSettings().getInitialSocialCapital();
    }

    public static double getPrice(Service service){
        return getServiceDataSettings().getServicePriceTable().get(service);
    }

    public static boolean newService() {
        return MathUtils.calculateProbability(100 - (int) getServiceCompanyPercentage());
    }

    private static double getServiceCompanyPercentage() {
        return ((double)(Simulation.getServiceCompanySize()*5)/(double)(1+Simulation.getCompanyListCopy().size()))*100.0;
    }

    public static double getPriceChange() {
        return getServiceDataSettings().getPriceChange();
    }

    public static double getCloseLimit(){
        return getServiceDataSettings().getCloseLimit();
    }
}
