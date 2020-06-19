package backend.server.commands.settings;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Product;
import backend.model.simulation.settings.settingsData.data.ProviderData;
import backend.server.servlets.FrontCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UpdateProviderDataCommand extends FrontCommand {
    @Override
    public void process() {
        ProviderData providerData = getProviderData();
        request.getSession(true).setAttribute(ProviderData.class.getSimpleName(), providerData);
    }

    private ProviderData getProviderData() {
        return new ProviderData(getAbsoluteDoubleParameter("initialSocialCapital"),getWorkerSalaries(),
                getAbsoluteDoubleParameter("priceChange"), -getAbsoluteDoubleParameter("closeLimit"));
    }


    private Map<Product, Double> getWorkerSalaries() {
        String[] productSalePrices = request.getParameterValues("productSalePrices[]");
        Map<Product,Double> table = new HashMap<>();
        Arrays.stream(productSalePrices)
                .map(group -> group.split(","))
                .forEach(group -> addToGroup(table, Product.valueOf(group[0]),Double.parseDouble(group[1])));
        if(table.size()==Product.values().length)return table;
        else return getOldTable();
    }

    private Map<Product, Double> getOldTable() {
        return ((ProviderData) request.getSession(true).getAttribute(ProviderData.class.getSimpleName())).getProductSalePriceTable();
    }

    private void addToGroup(Map<Product, Double> table, Product product, double salePrice) {
        if(salePrice > 0)table.put(product, salePrice);
    }
}
