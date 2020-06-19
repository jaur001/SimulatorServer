package backend.server.commands.settings;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulation.settings.settingsData.data.ServiceData;
import backend.server.servlets.FrontCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UpdateServiceDataCommand extends FrontCommand {
    @Override
    public void process() {
        ServiceData providerData = getProviderData();
        request.getSession(true).setAttribute(ServiceData.class.getSimpleName(), providerData);
    }

    private ServiceData getProviderData() {
        return new ServiceData(getAbsoluteDoubleParameter("initialSocialCapital"), getServicePriceTable(),
                getAbsoluteDoubleParameter("priceChange"), -getAbsoluteDoubleParameter("closeLimit"));
    }


    private Map<Service, Double> getServicePriceTable() {
        String[] serviceSalePrices = request.getParameterValues("serviceSalePrices[]");
        Map<Service,Double> table = new HashMap<>();
        Arrays.stream(serviceSalePrices)
                .map(group -> group.split(","))
                .forEach(group -> addToGroup(table, Service.valueOf(group[0]),Double.parseDouble(group[1])));
        if(table.size()==Service.values().length)return table;
        else return getOldTable();
    }

    private Map<Service, Double> getOldTable() {
        return ((ServiceData) request.getSession(true).getAttribute(ServiceData.class.getSimpleName())).getServicePriceTable();
    }

    private void addToGroup(Map<Service, Double> table, Service service, double salePrice) {
        if(salePrice > 0)table.put(service, salePrice);
    }
}
