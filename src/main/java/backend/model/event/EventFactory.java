package backend.model.event;

public interface EventFactory {

    Event buildEvent(Object data);

    static void addEvent(Event event){
        EventController.addEvent(event);
    }
}
