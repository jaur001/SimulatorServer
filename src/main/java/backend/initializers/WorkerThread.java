package backend.initializers;


import backend.model.simulables.person.worker.Quality;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.WorkerSettings;
import backend.utils.MathUtils;

import java.util.List;

public class WorkerThread extends Thread {
    public static void setJobs(List<Worker> workerList){
        workerList.parallelStream().forEach(WorkerThread::setJob);
    }

    private static void setJob(Worker worker){
        worker.setJob(WorkerSettings.selectJob());
        int position = MathUtils.random(0,Quality.values().length);
        worker.setQuality(Quality.values()[position]);
    }
}
