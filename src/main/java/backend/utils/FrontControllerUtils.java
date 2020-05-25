package backend.utils;

import backend.model.simulation.settings.settingsList.*;
import backend.model.simulation.timeLine.Speed;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.commands.bills.DownloadCommand;
import backend.server.commands.bills.ShowBillsCommand;
import backend.server.commands.quickSettings.ChangeSpeedCommand;
import backend.server.commands.quickSettings.UpdateQuickSettingsCommand;
import backend.server.commands.settings.*;
import backend.server.commands.simulables.FollowSimulableCommand;
import backend.server.commands.simulables.SearchCommand;
import backend.server.commands.simulables.UnfollowSimulableCommand;
import backend.server.commands.tables.ShowClientsCommand;
import backend.server.commands.tables.ShowProvidersCommand;
import backend.server.commands.tables.ShowRestaurantsCommand;
import backend.server.commands.tables.ShowWorkersCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class FrontControllerUtils {

    private static Map<String, List<String>> folderTable = new LinkedHashMap<>();

    static {
        String[] folders = {"bills.","settings.","tables.","simulables.","quickSettings."};
        List<String> billsFolder = Arrays.asList(DownloadCommand.class.getSimpleName(), ShowBillsCommand.class.getSimpleName());
        List<String> settingsFolder = Arrays.asList(CancelCommand.class.getSimpleName(), SaveSettingsCommand.class.getSimpleName(),
                ShowSettingsCommand.class.getSimpleName(),UpdateGeneralDataCommand.class.getSimpleName(),UpdateClientDataCommand.class.getSimpleName(),
                UpdateRestaurantDataCommand.class.getSimpleName(),UpdateProviderDataCommand.class.getSimpleName(),UpdateServiceDataCommand.class.getSimpleName()
                ,UpdateWorkerDataCommand.class.getSimpleName());
        List<String> tablesFolder = Arrays.asList(ShowClientsCommand.class.getSimpleName(), ShowProvidersCommand.class.getSimpleName(),
                ShowRestaurantsCommand.class.getSimpleName(), ShowWorkersCommand.class.getSimpleName());
        List<String> simulablesFolder = Arrays.asList(SearchCommand.class.getSimpleName(), FollowSimulableCommand.class.getSimpleName(),
                UnfollowSimulableCommand.class.getSimpleName());
        List<String> quickSettingsFolder = Arrays.asList(ChangeSpeedCommand.class.getSimpleName(), UpdateQuickSettingsCommand.class.getSimpleName());
        folderTable.put(folders[0],billsFolder);
        folderTable.put(folders[1],settingsFolder);
        folderTable.put(folders[2],tablesFolder);
        folderTable.put(folders[3],simulablesFolder);
    }

    public static String getFolder(String name){
        return folderTable.keySet().stream().filter(folder -> folderTable.get(folder).contains(name)).findFirst().orElse("");
    }

    public static void setQuickSettings(HttpServletRequest request) {
        request.getSession(true).setAttribute(Speed.class.getSimpleName(), TimeLine.getSpeed());
        request.getSession(true).setAttribute("clientProb", ClientSettings.getClientProbability());
        request.getSession(true).setAttribute("restaurantProb", RestaurantSettings.getRestaurantProbability());
        request.getSession(true).setAttribute("providerProb", ProviderSettings.getProviderProbability());
        request.getSession(true).setAttribute("serviceProb", ServiceSettings.getServiceProbability());
        request.getSession(true).setAttribute("workerProb", WorkerSettings.getWorkerProbability());
    }
}
