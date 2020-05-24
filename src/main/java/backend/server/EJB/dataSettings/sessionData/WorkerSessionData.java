package backend.server.EJB.dataSettings.sessionData;

import backend.server.EJB.dataSettings.GenericSessionData;
import backend.server.EJB.dataSettings.data.WorkerData;

public class WorkerSessionData extends GenericSessionData {

    private WorkerData workerData;

    public WorkerSessionData() {
        super();
    }

    @Override
    public void init(Object data) {
        if(data instanceof WorkerData) workerData = (WorkerData) data;
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultWorkerData());
    }

    public WorkerData getWorkerData() {
        return workerData;
    }
}
