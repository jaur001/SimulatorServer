package backend.model.simulation.timeLine;


import backend.model.event.EventController;
import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.person.client.Client;

import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeLine extends EventGenerator{

    public static int TIMEOUT = 100;
    private static SimulationDate date = new SimulationDate();
    private List<Simulable> simulableList;
    SimulationController controller;

    public TimeLine(List<Simulable> simulableList) {
        this.simulableList = simulableList;
        controller = new SimulationController(simulableList);
        date = new SimulationDate();
    }

    public void play(){
        simulableList.parallelStream().forEach(Simulable::simulate);
        passDay();
        SimulableTester.changeSimulable(null);
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

    public void addSimulable(Simulable simulable) {
        controller.addSimulableAtTheEnd(simulable);
    }

    public void removeSimulable(Simulable simulable) {
        controller.removeSimulableAtTheEnd(simulable);
    }
}
