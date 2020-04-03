package backend.model.event.events;

import backend.model.event.Event;

import java.util.Date;

public class DateEvent implements Event {

    private Date date;

    public DateEvent(Date date) {
        this.date = date;
    }

    @Override
    public String getMessage() {
        return date.toString();
    }
}
