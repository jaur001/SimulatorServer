package backend.model.simulation;

import backend.model.simulables.Simulable;

import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeLine {

    private static Date date = new Date();
    private List<Simulable> simulableList;

    public TimeLine(List<Simulable> simulableList) {
        this.simulableList = simulableList;
    }

    public static boolean isLastDay() {
        return TimeLine.getDay() == getLengthOfMonth();
    }

    private static int getLengthOfMonth() {
        return Month.of(TimeLine.getMonth()).minLength();
    }

    public void play(){
        simulableList.parallelStream().forEach(Simulable::simulate);
        passDay();
    }

    private void passDay() {
        date.setDate(date.getDate()+1);
        System.out.println("New Day:" + date.toString());
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Simulation stopped");
        }
    }

    public static Date getDate() {
        return date;
    }

    public static int getYear() {
        return date.getYear();
    }

    public static int getMonth() {
        return date.getMonth();
    }

    public static int getDay() {
        return date.getDate();
    }

}
