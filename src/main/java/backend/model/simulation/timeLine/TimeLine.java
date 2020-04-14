package backend.model.simulation.timeLine;


import backend.model.event.EventController;
import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.SimulableTester;
import backend.model.simulation.administration.SimulationAdministration;

import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeLine extends EventGenerator{

    public static int TIMEOUT = 100;
    private static SimulationDate date = new SimulationDate();
    private List<Simulable> simulableList;
    SimulationAdministration controller;

    public static Simulable actualSimulable;

    public TimeLine(List<Simulable> simulableList) {
        this.simulableList = simulableList;
        controller = new SimulationAdministration(simulableList);
        date = new SimulationDate();
    }


    public void play(){
        simulableList.forEach(Simulable::simulate);
        SimulableTester.changeSimulable(null);
        passDay();
        controller.manageSimulation();
        EventController.resizeList();
    }


    private void passDay() {
        date.setDate(date.getDate()+1);
        addEvent(date);
        try {
            TimeUnit.MILLISECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            System.out.println("Simulation stopped");
        }
    }



    public static void setTIMEOUT(int TIMEOUT) {
        TimeLine.TIMEOUT = TIMEOUT;
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

    public static int getYear() {
        return date.getYear()+1900;
    }

    public static int getMonth() {
        return date.getMonth();
    }

    public static int getDay() {
        return date.getDate();
    }

    public void removeSimulable(Simulable simulable) {
        controller.removeSimulable(simulable);
    }
}
