package backend.model.simulables;

import backend.model.event.Event;
import backend.model.simulables.bank.EconomicAgent;

public interface Simulable extends Event, EconomicAgent {
    void simulate();
    
}
