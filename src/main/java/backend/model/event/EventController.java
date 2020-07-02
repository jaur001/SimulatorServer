package backend.model.event;

import backend.model.simulation.administration.SimulatorThreadPool;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;
import backend.model.simulation.administration.centralControl.SimulatorSwitcher;
import backend.model.simulation.timeLine.TimeLine;
import backend.utils.SimulationFileWriter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventController {

    public static final int EVENT_LIMIT = 300;
    private static List<Event> eventList = new CopyOnWriteArrayList<>();
    private static List<Event> pendingEvents = new CopyOnWriteArrayList<>();

    public static void addEvent(Event event){
        if(isTheFirstDay()) return;
        System.out.println(event.getMessage());
        SimulationFileWriter.append(event.getMessage(),SimulatorSwitcher.getUriEvents());
        if(event.isFollowed(SimulationFollowAdministrator.getFollowedSimulables())) SimulatorThreadPool.executeTask(() -> addToWeb(event));
    }


    public static void addToWeb(Event event) {
        try {
            eventList.add(event);
            pendingEvents.add(event);
        } catch (ConcurrentModificationException | IndexOutOfBoundsException e){
            SimulatorSwitcher.waitForOtherElements();
            addToWeb(event);
        }
    }

    private static boolean isTheFirstDay() {
        return TimeLine.isSameDate(new Date());
    }

    public static String getPendingEvents(){
        String events = getEvents(pendingEvents);
        pendingEvents = new CopyOnWriteArrayList<>();
        return events;
    }

    public static String getAllEvents(){
        return getEvents(eventList);
    }

    public static String getEvents(List<Event> eventList){
        StringBuilder events = new StringBuilder();
        eventList.stream()
                .limit(EVENT_LIMIT)
                .forEach(event -> events.insert(0,"<p>" + event.getMessage() + "</p>"));
        return events.toString();
    }

    public static void resetEvents() {
        eventList = new CopyOnWriteArrayList<>();
    }

    public static void checkEvents(){
        if(eventList.size() > EVENT_LIMIT)eventList.subList(eventList.size()-EVENT_LIMIT,eventList.size());
    }
}
