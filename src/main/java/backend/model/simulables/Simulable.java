package backend.model.simulables;

import backend.model.simulables.bank.EconomicAgent;

public interface Simulable extends EconomicAgent {
    void simulate();
    int getNIF();
    String getName();

}
