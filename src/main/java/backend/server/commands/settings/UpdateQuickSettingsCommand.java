package backend.server.commands.settings;

import backend.model.simulables.company.Company;
import backend.model.simulation.settings.settingsList.*;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.servlets.FrontCommand;
import backend.utils.FrontControllerUtils;

public class UpdateQuickSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        updateQuickSettings();
        FrontControllerUtils.setQuickSettings(request);
    }

    private void updateQuickSettings() {
        TimeLine.setSpeed(Integer.parseInt(request.getParameter("speed")));
        Company.setTaxes(getAbsoluteDoubleParameter("taxes")/100.0);
        ClientSettings.setClientProbability(getAbsoluteDoubleParameter("clientProb")/100.0);
        RestaurantSettings.setRestaurantProbability(getAbsoluteDoubleParameter("restaurantProb")/100.0);
        ProviderSettings.setProviderProbability(getAbsoluteDoubleParameter("providerProb")/100.0);
        ServiceSettings.setServiceProbability(getAbsoluteDoubleParameter("serviceProb")/100.0);
        WorkerSettings.setWorkerProbability(getAbsoluteDoubleParameter("workerProb")/100.0);
    }
}
