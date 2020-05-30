package backend.view.loaders.database.builder.builders;

import backend.server.EJB.dataSettings.data.GeneralData;
import backend.server.EJB.dataSettings.data.ServiceData;
import backend.server.EJB.dataSettings.data.WorkerData;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class WorkerDataBuilder extends Builder<WorkerData> {

    @Override
    public String getHeader() {
        return WorkerData.class.getSimpleName();
    }

    @Override
    protected List<Object> getRow(WorkerData workerData) {
        return Arrays.asList("0",workerData.getMinSalary(),workerData.getSalaryChange()
                ,workerData.getSalaryDesiredChange(),workerData.getRetireAge(),workerData.getPercentageRetirement());
    }

    @Override
    protected WorkerData getItem(Object[] parameters) {
        return new WorkerData((double)parameters[1],(double)parameters[2],(double)parameters[3],(int)parameters[4],(double)parameters[5]);
    }
}
