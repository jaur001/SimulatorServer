package backend.server.commands.simulables;

import backend.server.servlets.FrontCommand;

public class FollowSimulableCommand extends FrontCommand {

    @Override
    public void process() {
        Integer NIF = Integer.parseInt(request.getParameter("NIF"));
        System.out.println(NIF);
    }
}
