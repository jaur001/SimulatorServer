package backend.model.event;

import backend.model.simulation.timeLine.SimulationDate;
import backend.model.simulation.timeLine.TimeLine;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EventController {

    private static final int MAX_EVENTS_PER_DAY = 20;
    private static AtomicInteger count = new AtomicInteger(0);
    private static List<String> eventList = new LinkedList<>();

    public static void addEvent(Event event){
        if(isTheFirstDay()) return;
        if(event instanceof SimulationDate) reset();
        if(count.getAndIncrement() < MAX_EVENTS_PER_DAY) System.out.println(event.getMessage());
        addToWeb(event);
    }

    public static void addToWeb(Event event) {
        if(eventList.size()>= 1000) eventList.remove(0);
        eventList.add(event.getMessage());
    }

    public static void reset(){
        count.set(0);
        eventList = new LinkedList<>();
    }

    private static boolean isTheFirstDay() {
        return TimeLine.isSameDate(new Date());
    }

    public static List<String> getEvents(){
        return eventList;
    }



}
