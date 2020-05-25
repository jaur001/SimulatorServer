package backend.server.commands.quickSettings;

import backend.model.simulation.timeLine.Speed;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.servlets.FrontCommand;

public class ChangeSpeedCommand extends FrontCommand {
    @Override
    public void process() {
        TimeLine.setSpeed(Integer.parseInt(request.getParameter("speed")));
        request.getSession(true).setAttribute(Speed.class.getSimpleName(), TimeLine.getSpeed());
    }
}
