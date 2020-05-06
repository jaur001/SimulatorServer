package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.server.EJB.dataSettings.GenericDataSettings;
import backend.server.EJB.dataSettings.data.ProviderData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.LinkedHashMap;
import java.util.Map;

@Stateful(name = "ProviderSettingsStatefulEJB")
public class ProviderSettingsStatefulBean extends GenericDataSettings {

    private double initialSocialCapital;
    private Map<Product, Integer> productCostTable = new LinkedHashMap<>();

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof ProviderData){
            ProviderData providerData = (ProviderData) data;
            initialSocialCapital = providerData.getInitialSocialCapital();
            productCostTable = providerData.getProductCostTable();
        }
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultProviderSettings());
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public Map<Product, Integer> getProductCostTable() {
        return productCostTable;
    }
}
