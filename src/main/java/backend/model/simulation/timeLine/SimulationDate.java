package backend.model.simulation.timeLine;

import backend.model.event.Event;

import java.util.Date;

public class SimulationDate extends Date implements Event {
    @Override
    public String getMessage() {
        return this.toString();
    }
}
