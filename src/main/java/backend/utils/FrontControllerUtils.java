package backend.utils;

import backend.server.commands.bills.ShowBillsCommand;

import java.util.*;

public class FrontControllerUtils {

    private static Map<String, List<String>> folderTable = new LinkedHashMap<>();

    static {
        String[] folders = {"bills.","settings.","simulables."};
        List<String> billsFolder = Arrays.asList("DownloadCommand", "ShowBillsCommand");
        List<String> settingsFolder = Arrays.asList("CancelCommand", "SaveSettingsCommand","ShowSettingsCommand","ChangeSpeedCommand");
        List<String> simulablesFolder = Arrays.asList("ShowClientsCommand", "ShowProvidersCommand","ShowRestaurantsCommand");
        folderTable.put(folders[0],billsFolder);
        folderTable.put(folders[1],settingsFolder);
        folderTable.put(folders[2],simulablesFolder);
    }

    public static String getFolder(String name){
        return folderTable.keySet().stream().filter(folder -> folderTable.get(folder).contains(name)).findFirst().orElse("");
    }
}
