package backend.server.commands.settings;

import backend.server.EJB.dataSettings.data.GeneralData;
import backend.server.servlets.FrontCommand;

public class UpdateGeneralDataCommand extends FrontCommand {
    @Override
    public void process() {
        GeneralData generalData = getGeneralData();
        request.getSession(true).setAttribute(GeneralData.class.getSimpleName(), generalData);
    }

    private GeneralData getGeneralData() {
        return new GeneralData(getAbsoluteIntParameter("clientCount"), getAbsoluteIntParameter("restaurantCount"),
                getAbsoluteIntParameter("providerCount"), getAbsoluteIntParameter("serviceCount"), getAbsoluteIntParameter("workerCount"));
    }
}
