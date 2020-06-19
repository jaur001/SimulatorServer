package backend.model.simulation.settings.settingsData.sessionData;

import backend.model.simulation.settings.settingsData.AdjustableSettingsData;
import backend.model.simulation.settings.settingsData.data.WorkerData;

public class WorkerSessionData extends AdjustableSettingsData {

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
