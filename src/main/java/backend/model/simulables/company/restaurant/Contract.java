package backend.model.simulables.company.restaurant;

import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.timeLine.TimeLine;

import java.util.Date;


public class Contract {
    private Worker worker;
    private Date expireDate;

    public Contract(Worker worker, Date expireDate) {
        this.worker = worker;
        this.expireDate = expireDate;
    }

    public Worker getWorker() {
        return worker;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public boolean isExpired(){
        return TimeLine.getDate().after(expireDate);
    }

    public boolean isExpiredSoon(){
        Date date = (Date) expireDate.clone();
        date.setDate(date.getDate()-15);
        return TimeLine.getDate().after(date);
    }
}
