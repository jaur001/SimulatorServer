package backend.server.commands.settings;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.server.EJB.dataSettings.data.ProviderData;
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
                getAbsoluteDoubleParameter("priceChange"), -getAbsoluteIntParameter("closeLimit"));
    }


    private Map<Product, Integer> getWorkerSalaries() {
        String[] productSalePrices = request.getParameterValues("productSalePrices[]");
        Map<Product,Integer> table = new HashMap<>();
        Arrays.stream(productSalePrices)
                .map(group -> group.split(","))
                .forEach(group -> addToGroup(table, Product.valueOf(group[0]),Integer.parseInt(group[1])));
        if(table.size()==Product.values().length)return table;
        else return getOldTable();
    }

    private Map<Product, Integer> getOldTable() {
        return ((ProviderData) request.getSession(true).getAttribute(ProviderData.class.getSimpleName())).getProductSalePriceTable();
    }

    private void addToGroup(Map<Product, Integer> table, Product product, int salePrice) {
        if(salePrice > 0)table.put(product, (int)salePrice);
    }
}
