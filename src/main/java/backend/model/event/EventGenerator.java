package backend.model.event;


public abstract class EventGenerator {

    protected void addEvent(Event event) {
        if(event.getMessage().equals("")){
            System.out.println("Problem");
        }
        EventController.addEvent(event);
    }

}
