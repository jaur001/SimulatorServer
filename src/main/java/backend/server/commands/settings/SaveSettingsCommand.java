package backend.server.commands.settings;

import backend.server.EJB.dataSettings.SettingsBuilder;
import backend.server.EJB.dataSettings.data.GeneralData;
import backend.server.servlets.FrontCommand;

public class SaveSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        SettingsBuilder.build(getGeneralData());
        forward("/index.jsp");
    }

    private GeneralData getGeneralData() {
        return new GeneralData(getIntParameter("clients"),getIntParameter("restaurants"),getIntParameter("providers"),0,0);
    }
}
