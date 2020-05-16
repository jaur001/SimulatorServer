package backend.server.commands.settings;

import backend.server.EJB.dataSettings.MinMaxData;
import backend.server.EJB.dataSettings.data.ClientData;
import backend.server.servlets.FrontCommand;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class UpdateClientDataCommand extends FrontCommand {
    @Override
    public void process() {
        ClientData clientData = getClientData();
        request.getSession(true).setAttribute(ClientData.class.getSimpleName(), clientData);
    }

    private ClientData getClientData() {
        return new ClientData(getDoubleParameter("salaryMean"),getDoubleParameter("salarySd"),
                getDoubleParameter("salaryMin"),getRestaurantGroups(),getInvitedPeople(),getNumOfRestaurant());
    }

    private Map<Integer, Integer> getRestaurantGroups() {
        String[] restaurantGroups = request.getParameterValues("restaurantGroups[]");
        Map<Integer,Integer> table = new LinkedHashMap<>();
        Arrays.stream(restaurantGroups)
                .forEach(group -> addGroup(table,group));
        return table;
    }

    private void addGroup(Map<Integer, Integer> table, String group) {
        String[] groupPair = group.split(",");
        if(groupPair.length>=2)table.put(Integer.parseInt(groupPair[0]),Integer.parseInt(groupPair[1]));
    }

    private MinMaxData getInvitedPeople() {
        return new MinMaxData(getIntParameter("invitedPeopleMin"),getIntParameter("invitedPeopleMax"));
    }

    private MinMaxData getNumOfRestaurant() {
        return new MinMaxData(getIntParameter("numOfRestaurantMin"),getIntParameter("numOfRestaurantMax"));
    }
}
