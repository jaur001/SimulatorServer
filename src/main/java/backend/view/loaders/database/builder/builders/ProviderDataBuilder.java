package backend.view.loaders.database.builder.builders;

import backend.server.EJB.dataSettings.MinMaxData;
import backend.server.EJB.dataSettings.data.GeneralData;
import backend.server.EJB.dataSettings.data.ProviderData;
import backend.server.EJB.dataSettings.data.RestaurantData;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class ProviderDataBuilder extends Builder<ProviderData> {

    @Override
    public String getHeader() {
        return ProviderData.class.getSimpleName();
    }

    @Override
    protected List<Object> getRow(ProviderData providerData) {
        return Arrays.asList("0",providerData.getInitialSocialCapital(),providerData.getPriceChange()
                ,providerData.getCloseLimit());
    }

    @Override
    protected ProviderData getItem(Object[] parameters) {
        return new ProviderData((double)parameters[1],(double)parameters[2],(double)parameters[3]);
    }
}
