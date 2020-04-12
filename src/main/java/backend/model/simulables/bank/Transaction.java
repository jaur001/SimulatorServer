package backend.model.simulables.bank;

public abstract class Transaction {
    protected EconomicAgent issuer;
    protected EconomicAgent receiver;
    protected double amount;

    public Transaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        this.issuer = issuer;
        this.receiver = receiver;
        this.amount = amount;
    }


    public EconomicAgent getIssuer() {
        return issuer;
    }

    public EconomicAgent getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void makeTransaction(){
        generateBill();
        pay();
    }

    protected abstract boolean checkBill();

    protected abstract void pay();

    protected abstract void generateBill();

    protected void makeNormalTransaction(){
        issuer.pay(amount);
        receiver.collect(amount);
    }

    protected void makeInverseTransaction(){
        receiver.pay(amount);
        issuer.collect(amount);
    }
}
