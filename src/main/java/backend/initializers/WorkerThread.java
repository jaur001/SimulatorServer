package backend.initializers;


import backend.implementations.jobSelector.ProportionalJobSelector;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.JobSelector;
import backend.model.simulables.person.worker.Quality;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;
import backend.utils.MathUtils;

import java.util.List;
import java.util.stream.IntStream;

public class WorkerThread extends Thread {
    public static void setJobs(List<Worker> workerList){
        workerList.parallelStream().forEach(WorkerThread::setJob);
        if(workerList.size()>=Job.values().length)setAtLeastOneWorkerPerJob(workerList);
    }

    private static void changeAge(Worker worker) {
        worker.getPersonalData().setBirthDate(getMonth() + "/"+ getDay() + "/" + getYear());
    }

    private static int getMonth(){
        return MathUtils.random(1,13);
    }

    private static int getDay(){
        return MathUtils.random(1,29);
    }

    private static int getYear(){
        return MathUtils.random(1965,2000);
    }

    public static void setJob(Worker worker){
        setJob(worker,new ProportionalJobSelector());
    }

    public static void setJob(Worker worker, JobSelector selector){
        if(WorkerSettings.isInRetireAge(worker))changeAge(worker);
        worker.setJob(selector.selectJob().toString());
        worker.setSalaryDesired(RestaurantSettings.getSalary(Job.valueOf(worker.getJob())));
        int quality = MathUtils.random(0,Quality.values().length);
        worker.setQuality(Quality.values()[quality]);
    }

    private static void setAtLeastOneWorkerPerJob(List<Worker> workerList) {
        IntStream.range(0, Job.values().length).boxed()
                .forEach(integer -> workerList.get(integer).setJob(Job.values()[integer].toString()));
    }
}
