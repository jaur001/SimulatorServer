package backend.model.simulation.timeLine;


import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeLine extends EventGenerator{

    public static int TIMEOUT = 1000;
    private static SimulationDate date = new SimulationDate();
    private List<Simulable> simulableList;

    public static void setTIMEOUT(int TIMEOUT) {
        TimeLine.TIMEOUT = TIMEOUT;
    }

    public TimeLine(List<Simulable> simulableList) {
        this.simulableList = simulableList;
        date = new SimulationDate();
    }

    public static boolean isLastDay() {
        return TimeLine.getDay() == getLengthOfMonth();
    }

    private static int getLengthOfMonth() {
        return Month.of(TimeLine.getMonth()+1).minLength();
    }

    public void play(){
        passDay();
        simulableList.parallelStream().forEach(Simulable::simulate);
    }

    private void passDay() {
        date.setDate(date.getDate()+1);
        System.out.println("New Day:" + date.toString());
        if(WorkerSettings.newWorker()) Simulation.addWorker();
        addEvent((SimulationDate) date.clone());
        try {
            TimeUnit.MILLISECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            System.out.println("Simulation stopped");
        }
    }

    public static boolean isSameDate(Date otherDate){
        return otherDate.getYear()== date.getYear() && otherDate.getMonth()==date.getMonth() && otherDate.getDate()==date.getDate();
    }

    public static Date getDate() {
        return date;
    }

    public static int getYear() {
        return date.getYear()+1900;
    }

    public static int getMonth() {
        return date.getMonth();
    }

    public static int getDay() {
        return date.getDate();
    }



}
