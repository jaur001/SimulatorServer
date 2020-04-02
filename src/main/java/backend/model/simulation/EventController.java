package backend.model.simulation;

import backend.model.event.Event;

import java.util.LinkedList;
import java.util.List;

public class EventController {

    public static final List<Event> eventList = new LinkedList<>();

    public static void addEvent(Event event){
        eventList.add(event);
    }

    public static boolean thereIsEvent(){
        return eventList.size()>0;
    }

    public static Event getEvent(){
        Event event = eventList.get(0);
        eventList.remove(0);
        return event;
    }


}
