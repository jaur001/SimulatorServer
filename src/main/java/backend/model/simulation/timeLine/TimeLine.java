package backend.model.simulation.timeLine;


import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.SimulableTester;

import java.time.Month;
import java.util.Date;
import java.util.List;

public class TimeLine extends EventGenerator{

    private static Speed speed = new Speed();
    private static SimulationDate date = new SimulationDate();
    private List<Simulable> simulableList;

    public static Simulable actualSimulable;

    public TimeLine(List<Simulable> simulableList) {
        this.simulableList = simulableList;
        date = new SimulationDate();
    }

    public List<Simulable> getSimulableList() {
        return simulableList;
    }


    public void play(){
        simulableList.parallelStream().forEach(Simulable::simulate);
        SimulableTester.changeSimulable(null);
        passDay();
    }


    private void passDay() {
        date.setDate(date.getDate()+1);
        addEvent((SimulationDate)date.clone());
    }

    public static int getTimeOut(){
        return speed.getTimeout();
    }

    public static int getSpeed() {
        return speed.getSpeed();
    }

    public static void setSpeed(int speedToChange) {
        speed.setSpeed(speedToChange);
    }

    public static boolean isLastDay() {
        return TimeLine.getDay() == getLengthOfMonth();
    }

    private static int getLengthOfMonth() {
        return Month.of(TimeLine.getMonth()+1).minLength();
    }

    public static boolean isSameDate(Date otherDate){
        return otherDate.getYear()== date.getYear() && otherDate.getMonth()==date.getMonth() && otherDate.getDate()==date.getDate();
    }

    public static Date getDate() {
        return date;
    }

    public static Date getCloneDate(){
        return (Date) TimeLine.getDate().clone();
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
