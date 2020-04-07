package backend.initializers;


import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Quality;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;
import backend.utils.MathUtils;

import java.util.List;

public class WorkerThread extends Thread {
    public static void setJobs(List<Worker> workerList){
        workerList.parallelStream().forEach(WorkerThread::setJob);
    }

    public static void setJob(Worker worker){
        worker.setJob(WorkerSettings.selectJob());
        worker.setSalaryDesired(RestaurantSettings.getSalary(Job.valueOf(worker.getJob())));
        int position = MathUtils.random(0,Quality.values().length);
        worker.setQuality(Quality.values()[position]);
    }
}
