package backend.model.event.events;

import backend.model.bill.bills.Payroll;
import backend.model.event.Event;

public class PayrollEvent implements Event {

    private Payroll payroll;

    public PayrollEvent(Payroll payroll) {
        this.payroll = payroll;
    }

    @Override
    public String getMessage() {
        return "The " + payroll.getWorker().getJob() + ":"
                + payroll.getReceiverName() + " has received the payroll from "
                + payroll.getIssuerName() +", amount: " +payroll.getTotal();
    }
}
