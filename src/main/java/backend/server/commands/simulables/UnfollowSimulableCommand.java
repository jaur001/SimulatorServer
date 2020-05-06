package backend.server.commands.simulables;

import backend.server.EJB.FollowersControllerStatelessBean;
import backend.server.servlets.FrontCommand;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class UnfollowSimulableCommand extends FrontCommand {
    @Override
    public void process() {
        int NIF = Integer.parseInt(request.getParameter("NIF"));
        FollowersControllerStatelessBean followersController;
        {
            try {
                followersController = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/FollowersControllerStatelessEJB");
                followersController.unfollowSimulable(NIF);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
}
