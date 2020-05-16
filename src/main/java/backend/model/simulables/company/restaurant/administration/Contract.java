package backend.model.simulables.company.restaurant.administration;

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

    public boolean isExpired(){
        return TimeLine.isSameDate(expireDate);
    }

    public boolean isExpiredSoon(){
        Date date = getCopy();
        date.setDate(date.getDate()-15);
        return TimeLine.isSameDate(date);
    }


    private Date getCopy() {
        return new Date(expireDate.getYear(),expireDate.getMonth(),expireDate.getDate());
    }

    public Date getExpireDate() {
        return expireDate;
    }
}
