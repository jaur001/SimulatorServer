package backend.model.event;

import backend.model.simulables.Simulable;

import java.util.List;

public interface Event {

    String getMessage();
    boolean isFollowed(List<Simulable> simulableList);

}
