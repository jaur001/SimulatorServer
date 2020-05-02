package backend.model.simulation.timeLine;

import backend.model.event.Event;
import backend.model.simulables.Simulable;

import java.util.Date;
import java.util.List;

public class SimulationDate extends Date implements Event {
    @Override
    public String getMessage() {
        return this.toString();
    }

    @Override
    public boolean isFollowed(List<Simulable> simulableList) {
        return true;
    }
}
