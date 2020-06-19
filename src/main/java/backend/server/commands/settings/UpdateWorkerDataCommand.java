package backend.server.commands.settings;

import backend.model.simulation.settings.settingsData.data.WorkerData;
import backend.server.servlets.FrontCommand;

public class UpdateWorkerDataCommand extends FrontCommand {
    @Override
    public void process() {
        WorkerData workerData = getWorkerData();
        request.getSession(true).setAttribute(WorkerData.class.getSimpleName(), workerData);
    }

    private WorkerData getWorkerData() {
        return new WorkerData(getAbsoluteDoubleParameter("minSalary"), getAbsoluteDoubleParameter("salaryChange"),
                getAbsoluteDoubleParameter("salaryDesiredChange"),getAbsoluteIntParameter("retireAge"),
                getAbsoluteDoubleParameter("percentageRetirement"));
    }
}
