package backend.server.commands.settings;

import backend.model.simulation.timeLine.TimeLine;
import backend.server.servlets.FrontCommand;

public class ChangeSpeedCommand extends FrontCommand {
    @Override
    public void process() {
        TimeLine.setTIMEOUT(Integer.parseInt(request.getParameter("speed")));
    }
}
