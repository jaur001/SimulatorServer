package backend.model.event;


public abstract class EventGenerator {

    protected void addEvent(Event event) {
        EventController.addEvent(event);
    }

}
