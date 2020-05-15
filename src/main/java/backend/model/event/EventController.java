package backend.model.event;

import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.Simulator;
import backend.model.simulation.timeLine.TimeLine;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventController {

    public static final int MAX_SIZE = 1000;
    private static List<Event> eventList = new CopyOnWriteArrayList<>();

    public static void addEvent(Event event){
        if(isTheFirstDay()) return;
        System.out.println(event.getMessage());
        if(event.isFollowed(Simulation.getFollowedSimulables()))addToWeb(event);
    }


    public static void addToWeb(Event event) {
        try {
            eventList.add(event);
        } catch (ConcurrentModificationException | IndexOutOfBoundsException e){
            Simulator.waitForOtherElements();
            addToWeb(event);
        }
    }

    private static boolean isTheFirstDay() {
        return TimeLine.isSameDate(new Date());
    }

    public static List<Event> getEvents(){
        return eventList;
    }


    public static void resizeList() {
        if(eventList.size()>MAX_SIZE) eventList = eventList.subList(eventList.size()-MAX_SIZE,eventList.size());
    }

    public static void resetEvents() {
        eventList = new LinkedList<>();
    }
}
