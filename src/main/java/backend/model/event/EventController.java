package backend.model.event;

import backend.model.simulation.administration.SimulatorThreadPool;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;
import backend.model.simulation.administration.centralControl.SimulatorSwitcher;
import backend.model.simulation.timeLine.TimeLine;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventController {

    private static List<Event> eventList = new CopyOnWriteArrayList<>();

    public static void addEvent(Event event){
        if(isTheFirstDay()) return;
        System.out.println(event.getMessage());
        if(event.isFollowed(SimulationFollowAdministrator.getFollowedSimulables())) SimulatorThreadPool.executeTask(() -> addToWeb(event));
    }


    public static void addToWeb(Event event) {
        try {
            eventList.add(event);
        } catch (ConcurrentModificationException | IndexOutOfBoundsException e){
            SimulatorSwitcher.waitForOtherElements();
            addToWeb(event);
        }
    }

    private static boolean isTheFirstDay() {
        return TimeLine.isSameDate(new Date());
    }

    public static List<Event> getEvents(){
        List<Event> eventList = EventController.eventList;
        EventController.eventList = new CopyOnWriteArrayList<>();
        return eventList;
    }
}
