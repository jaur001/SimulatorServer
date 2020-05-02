package backend.model.event;

import backend.model.simulables.Simulable;

import java.util.List;

public abstract class GenericEvent<Simulable> implements Event{

    protected Simulable simulable;

    public GenericEvent(Simulable simulable) {
        this.simulable = simulable;
    }

    public Simulable getSimulable() {
        return simulable;
    }

    @Override
    public boolean isFollowed(List<backend.model.simulables.Simulable> simulableList){
        return simulableList.stream()
                .anyMatch(this::isInBill);
    }

    private boolean isInBill(backend.model.simulables.Simulable simulableFollowed) {
        if(simulable instanceof backend.model.simulables.Simulable){
            backend.model.simulables.Simulable auxSimulable = (backend.model.simulables.Simulable) simulable;
            return auxSimulable.getNIF()==simulableFollowed.getNIF();
        }
        return false;
    }
}
