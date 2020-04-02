package backend.model.simulation;

import backend.model.simulables.Simulable;

import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeLine {

    public static int TIMEOUT = 1000;
    private static Date date = new Date();
    private List<Simulable> simulableList;

    public static void setTIMEOUT(int TIMEOUT) {
        TimeLine.TIMEOUT = TIMEOUT;
    }

    public TimeLine(List<Simulable> simulableList) {
        this.simulableList = simulableList;
        date = new Date();
    }

    public static boolean isLastDay() {
        return TimeLine.getDay() == getLengthOfMonth();
    }

    private static int getLengthOfMonth() {
        return Month.of(TimeLine.getMonth()+1).minLength();
    }

    public void play(){
        simulableList.parallelStream().forEach(Simulable::simulate);
        passDay();
    }

    private void passDay() {
        date.setDate(date.getDate()+1);
        System.out.println("New Day:" + date.toString());
        try {
            TimeUnit.MILLISECONDS.sleep(TIMEOUT);
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
