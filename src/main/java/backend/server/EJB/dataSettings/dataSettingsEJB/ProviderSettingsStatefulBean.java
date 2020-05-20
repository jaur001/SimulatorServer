package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.server.EJB.dataSettings.GenericDataSettings;
import backend.server.EJB.dataSettings.data.ProviderData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.Map;

@Stateful(name = "ProviderSettingsStatefulEJB")
public class ProviderSettingsStatefulBean extends GenericDataSettings {

    private ProviderData providerData;

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof ProviderData){ providerData = (ProviderData) data;

        }
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultProviderSettings());
    }

    public double getInitialSocialCapital() {
        return providerData.getInitialSocialCapital();
    }

    public Map<Product, Integer> getProductSalePriceTable() {
        return providerData.getProductSalePriceTable();
    }

    public double getPriceChange() {
        return providerData.getPriceChange();
    }

    public int getCloseLimit() {
        return providerData.getCloseLimit();
    }

    public ProviderData getProviderData() {
        return providerData;
    }
}
