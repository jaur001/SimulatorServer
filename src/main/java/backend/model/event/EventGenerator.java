package backend.model.event;

import backend.model.simulation.timeLine.SimulationDate;
import backend.model.simulation.timeLine.TimeLine;

import java.util.Date;

public abstract class EventGenerator {
    protected void addEvent(Event event) {
        if(isTheFirstDay()) return;
        System.out.println(event.getMessage());
        EventController.addEvent(event);
    }

    private boolean isTheFirstDay() {
        return TimeLine.isSameDate(new Date());
    }
}
