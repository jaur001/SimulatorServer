package backend.model.simulation.timeLine;


import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.time.Month;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeLine extends EventGenerator{

    public static int TIMEOUT = 100;
    private static SimulationDate date = new SimulationDate();
    private List<Simulable> simulableList;
    private List<Simulable> addingList;
    private List<Simulable> removingList;

    public TimeLine(List<Simulable> simulableList) {
        this.simulableList = simulableList;
        this.addingList = new LinkedList<>();
        this.removingList = new LinkedList<>();
        date = new SimulationDate();
    }

    public void play(){
        simulableList.parallelStream().forEach(Simulable::simulate);
        passDay();
        SimulableTester.changeSimulable(null);
        checkAddedAndRemovedSimulables();
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

    private void checkAddedAndRemovedSimulables() {
        checkThereIsNewWorker();
        checkThereIsNewClient();
        checkAddingList();
        checkRemovingList();

    }

    private void checkThereIsNewWorker() {
        if(!WorkerSettings.newWorker()) return;
        Simulable simulable = Simulation.addWorker();
        if(simulable!=null)addSimulable(simulable);
    }

    private void checkThereIsNewClient() {
        if(!ClientSettings.newClient()) return;
        Simulable simulable = Simulation.addClient();
        if(simulable!=null)addSimulable(simulable);
    }

    private void checkAddingList() {
        if(addingList.size() > 0) simulableList.addAll(addingList);
        addingList = new LinkedList<>();
    }

    private void checkRemovingList() {
        if(removingList.size() > 0) simulableList.removeAll(removingList);
        removingList = new LinkedList<>();
    }

    public void addSimulable(Simulable simulable){
        addingList.add(simulable);
    }

    public void removeSimulable(Simulable simulable) {
        checkSimulable(simulable);
        removingList.add(simulable);
    }

    private void checkSimulable(Simulable simulable) {
        if(simulable instanceof Client) addEvent((Client)simulable);
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

}
