package backend.model.event;

import backend.model.bill.bills.EatingSale;

public class EatingSaleEvent implements Event {

    private EatingSale bill;

    public EatingSaleEvent(EatingSale bill) {
        this.bill = bill;
    }

    @Override
    public String getMessage() {
        return bill.getReceiverName() + " has gone to eat to " + bill.getIssuerName() + ", amount: " + bill.getTotal();
    }
}
