package backend.model.event;

import backend.model.event.Event;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventController {

    private static List<Event> eventList = new LinkedList<>();
    private static AtomicBoolean thereIsEvent = new AtomicBoolean(false);

    public static void addEvent(Event event){
        if(eventList.size()>= 1000)eventList.remove(0);
        eventList.add(event);
        thereIsEvent.set(true);
    }

    public static void reset(){
        eventList = new LinkedList<>();
    }

    public static boolean thereIsEvent(){
        return thereIsEvent.get();
    }

    public static List<Event> getEvents(){
        return eventList;
    }


}
