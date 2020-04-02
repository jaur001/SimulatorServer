package backend.model.event;

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
