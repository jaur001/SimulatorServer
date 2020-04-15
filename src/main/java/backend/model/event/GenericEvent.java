package backend.model.event;

public abstract class GenericEvent<Simulable> implements Event{

    protected Simulable simulable;

    public GenericEvent(Simulable simulable) {
        this.simulable = simulable;
    }

    public Simulable getSimulable() {
        return simulable;
    }
}
