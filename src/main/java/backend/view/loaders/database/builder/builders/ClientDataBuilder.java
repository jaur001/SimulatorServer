package backend.view.loaders.database.builder.builders;

import backend.utils.DistributionData;
import backend.utils.MinMaxData;
import backend.model.simulation.settings.settingsData.data.ClientData;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class ClientDataBuilder extends Builder<ClientData> {
    @Override
    public String getHeader() {
        return ClientData.class.getSimpleName();
    }

    @Override
    protected List<Object> getRow(ClientData clientData) {
        return Arrays.asList("0",clientData.getSalaryMean(),clientData.getSalarySd()
                ,clientData.getMinSalary(),clientData.getInvitedPeopleMin()
                ,clientData.getInvitedPeopleMax(),clientData.getNumOfRestaurantMin()
                ,clientData.getNumOfRestaurantMax(),clientData.getPlateNumberMean()
                ,clientData.getPlateNumberSd());
    }

    @Override
    protected ClientData getItem(Object[] parameters) {
        return new ClientData(new DistributionData((double)parameters[1],(double)parameters[2])
                ,(double)parameters[3],new MinMaxData((int)parameters[4],(int)parameters[5])
                ,new MinMaxData((int)parameters[6],(int)parameters[7])
                ,new DistributionData((double)parameters[8],(double)parameters[9]));
    }
}
