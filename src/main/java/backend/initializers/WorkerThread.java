package backend.initializers;


import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.List;

public class WorkerThread extends Thread {
    public static void setJobs(List<Worker> workerList){
        workerList.parallelStream().forEach(WorkerThread::setJob);
    }

    private static void setJob(Worker worker){
        worker.setJob(WorkerSettings.selectJob());
    }
}
