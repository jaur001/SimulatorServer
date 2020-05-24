package backend.server.commands.settings;

import backend.server.EJB.dataSettings.MinMaxData;
import backend.server.EJB.dataSettings.data.ClientData;
import backend.server.servlets.FrontCommand;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class UpdateClientDataCommand extends FrontCommand {
    @Override
    public void process() {
        ClientData clientData = getClientData();
        request.getSession(true).setAttribute(ClientData.class.getSimpleName(), clientData);
    }

    private ClientData getClientData() {
        return new ClientData(getAbsoluteDoubleParameter("salaryMean"), getAbsoluteDoubleParameter("salarySd"),
                getAbsoluteDoubleParameter("minSalary"),getRestaurantGroups(),getInvitedPeople(),getNumOfRestaurant());
    }

    private Map<Integer, Integer> getRestaurantGroups() {
        String[] restaurantGroups = request.getParameterValues("restaurantGroups[]");
        Map<Integer,Integer> table = new LinkedHashMap<>();
        Arrays.stream(restaurantGroups)
                .map(group -> group.split(","))
                .filter(group -> group.length<=2)
                .map(group -> new int[]{Integer.parseInt(group[0]),Integer.parseInt(group[1])})
                .sorted(Comparator.comparing(group -> group[0]))
                .forEach(group -> addToGroup(table, group[0], group[1]));
        if(table.size()==4)return table;
        else return getOldTable();
    }

    private Map<Integer, Integer> getOldTable() {
        return ((ClientData) request.getSession(true).getAttribute(ClientData.class.getSimpleName())).getRestaurantGroup();
    }

    private void addToGroup(Map<Integer, Integer> table, int salaryOption, int price) {
        if(price > 0)table.put(salaryOption, price);
    }

    private MinMaxData getInvitedPeople() {
        return new MinMaxData(getAbsoluteIntParameter("invitedPeopleMin"), getAbsoluteIntParameter("invitedPeopleMax"));
    }

    private MinMaxData getNumOfRestaurant() {
        return new MinMaxData(getAbsoluteIntParameter("numOfRestaurantMin"), getAbsoluteIntParameter("numOfRestaurantMax"));
    }
}
