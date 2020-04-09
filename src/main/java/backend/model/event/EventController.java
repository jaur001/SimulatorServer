package backend.model.event;

import backend.model.event.Event;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventController {

    private static List<String> eventList = new LinkedList<>();

    public static void addEvent(Event event){
        if(eventList.size()>= 1000) eventList.remove(0);
        eventList.add(event.getMessage());
    }

    public static void reset(){
        eventList = new LinkedList<>();
    }


    public static List<String> getEvents(){
        return eventList;
    }



}
