package backend.server.commands.quickSettings;

import backend.server.servlets.FrontCommand;
import backend.utils.FrontControllerUtils;

public class UpdateQuickSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        updateQuickSettings();
        FrontControllerUtils.setQuickSettings(request);
    }

    private void updateQuickSettings() {

    }
}
