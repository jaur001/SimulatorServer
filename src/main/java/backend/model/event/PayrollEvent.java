package backend.model.event;

import backend.model.bill.bills.Payroll;

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
