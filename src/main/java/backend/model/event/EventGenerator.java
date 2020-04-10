package backend.model.event;

import backend.model.simulation.timeLine.SimulationDate;
import backend.model.simulation.timeLine.TimeLine;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class EventGenerator {

    protected void addEvent(Event event) {
        EventController.addEvent(event);
    }

}
