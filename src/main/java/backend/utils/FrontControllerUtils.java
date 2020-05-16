package backend.utils;

import backend.server.commands.ChangeSpeedCommand;
import backend.server.commands.bills.DownloadCommand;
import backend.server.commands.bills.ShowBillsCommand;
import backend.server.commands.settings.*;
import backend.server.commands.simulables.FollowSimulableCommand;
import backend.server.commands.simulables.SearchCommand;
import backend.server.commands.simulables.UnfollowSimulableCommand;
import backend.server.commands.tables.ShowClientsCommand;
import backend.server.commands.tables.ShowProvidersCommand;
import backend.server.commands.tables.ShowRestaurantsCommand;
import backend.server.commands.tables.ShowWorkersCommand;

import java.util.*;

public class FrontControllerUtils {

    private static Map<String, List<String>> folderTable = new LinkedHashMap<>();

    static {
        String[] folders = {"bills.","settings.","tables.","simulables."};
        List<String> billsFolder = Arrays.asList(DownloadCommand.class.getSimpleName(), ShowBillsCommand.class.getSimpleName());
        List<String> settingsFolder = Arrays.asList(CancelCommand.class.getSimpleName(), SaveSettingsCommand.class.getSimpleName(),
                ShowSettingsCommand.class.getSimpleName(),UpdateGeneralDataCommand.class.getSimpleName(),UpdateClientDataCommand.class.getSimpleName());
        List<String> tablesFolder = Arrays.asList(ShowClientsCommand.class.getSimpleName(), ShowProvidersCommand.class.getSimpleName(),
                ShowRestaurantsCommand.class.getSimpleName(), ShowWorkersCommand.class.getSimpleName());
        List<String> simulablesFolder = Arrays.asList(SearchCommand.class.getSimpleName(), FollowSimulableCommand.class.getSimpleName(),
                UnfollowSimulableCommand.class.getSimpleName());
        folderTable.put(folders[0],billsFolder);
        folderTable.put(folders[1],settingsFolder);
        folderTable.put(folders[2],tablesFolder);
        folderTable.put(folders[3],simulablesFolder);
    }

    public static String getFolder(String name){
        return folderTable.keySet().stream().filter(folder -> folderTable.get(folder).contains(name)).findFirst().orElse("");
    }
}
