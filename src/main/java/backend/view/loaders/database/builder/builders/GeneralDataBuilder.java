package backend.view.loaders.database.builder.builders;

import backend.server.EJB.dataSettings.data.GeneralData;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class GeneralDataBuilder extends Builder<GeneralData> {
    @Override
    public String getHeader() {
        return GeneralData.class.getSimpleName();
    }

    @Override
    protected List<Object> getRow(GeneralData generalData) {
        return Arrays.asList("0",generalData.getClientCount(),generalData.getRestaurantCount()
                ,generalData.getProviderCount(),generalData.getServiceCount()
                ,generalData.getWorkerCount());
    }

    @Override
    protected GeneralData getItem(Object[] parameters) {
        return new GeneralData((int)parameters[1],(int)parameters[2],(int)parameters[3],(int)parameters[4],(int)parameters[5]);
    }
}
