package backend.model.simulation.settings.settingsData.settingsData;

import backend.model.simulation.settings.settingsData.AdjustableSettingsData;
import backend.model.simulation.settings.settingsData.data.WorkerData;

public class WorkerSettingsData extends AdjustableSettingsData {

    private WorkerData workerData;

    public WorkerSettingsData() {
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
