package backend.model.simulation.settings;

import backend.server.EJB.dataSettings.GenericSessionDataSettings;

public abstract class GenericSettings {
    public abstract void init(GenericSessionDataSettings dataSettings);
}
