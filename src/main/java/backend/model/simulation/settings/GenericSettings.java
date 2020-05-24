package backend.model.simulation.settings;

import backend.server.EJB.dataSettings.GenericSessionData;

public abstract class GenericSettings {
    public abstract void init(GenericSessionData dataSettings);
}
