package backend.view.loaders.database.builder.builders;

import backend.model.simulation.settings.settingsData.data.ServiceData;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class ServiceDataBuilder extends Builder<ServiceData> {

    @Override
    public String getHeader() {
        return ServiceData.class.getSimpleName();
    }

    @Override
    protected List<Object> getRow(ServiceData serviceData) {
        return Arrays.asList("0",serviceData.getInitialSocialCapital(),serviceData.getPriceChange()
                ,serviceData.getCloseLimit());
    }

    @Override
    protected ServiceData getItem(Object[] parameters) {
        return new ServiceData((double)parameters[1],(double)parameters[2],(double)parameters[3]);
    }
}
