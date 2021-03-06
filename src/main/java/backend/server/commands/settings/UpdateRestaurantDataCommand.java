package backend.server.commands.settings;

import backend.model.simulables.person.worker.Job;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.utils.MinMaxData;
import backend.model.simulation.settings.settingsData.data.ClientData;
import backend.model.simulation.settings.settingsData.data.RestaurantData;
import backend.server.servlets.FrontCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UpdateRestaurantDataCommand extends FrontCommand {
    @Override
    public void process() {
        RestaurantData restaurantData = getRestaurantData();
        request.getSession(true).setAttribute(RestaurantData.class.getSimpleName(), restaurantData);
    }

    private RestaurantData getRestaurantData() {
        return new RestaurantData(getAbsoluteDoubleParameter("initialSocialCapital"),getWorkerSalaries(), getLengthContract(),
                getAbsoluteDoubleParameter("priceChange"), getAbsoluteDoubleParameter("capacity"), -getAbsoluteDoubleParameter("closeLimit"));
    }

    private MinMaxData getLengthContract() {
        return new MinMaxData(getAbsoluteIntParameter("lengthContractMin"), getAbsoluteIntParameter("lengthContractMax"));
    }

    private Map<Job, Integer> getWorkerSalaries() {
        String[] jobSalaries = request.getParameterValues("jobSalaries[]");
        Map<Job,Integer> table = new HashMap<>();
        Arrays.stream(jobSalaries)
                .map(group -> group.split(","))
                .forEach(group -> addToGroup(table, Job.valueOf(group[0]),Integer.parseInt(group[1])));
        if(table.size()==Job.values().length)return table;
        else return getOldTable();
    }

    private Map<Job, Integer> getOldTable() {
        return ((RestaurantData) request.getSession(true).getAttribute(RestaurantData.class.getSimpleName())).getWorkerSalaryTable();
    }

    private void addToGroup(Map<Job, Integer> table, Job job, int salary) {
        if(salary > 0)
            if(salary< ((ClientData)request.getSession(true).getAttribute(ClientData.class.getSimpleName())).getMinSalary()) table.put(job, (int)ClientSettings.getMinSalary());
            else table.put(job,salary);
    }
}
