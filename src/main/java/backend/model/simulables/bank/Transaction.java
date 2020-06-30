package backend.model.simulables.bank;

public abstract class Transaction {
    protected EconomicAgent issuer;
    protected EconomicAgent receiver;
    protected double amount;
    protected double taxRate;

    public Transaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        this.issuer = issuer;
        this.receiver = receiver;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void makeTransaction(){
        taxRate = generateBill();
        if(taxRate != 0)pay();
    }

    protected abstract boolean checkBill();

    protected abstract void pay();

    protected abstract double generateBill();

    protected void makeNormalTransaction(){
        issuer.pay(amount*(1+taxRate));
        receiver.collect(amount);
    }

    protected void makeInverseTransaction(){
        receiver.pay(amount*(1+taxRate));
        issuer.collect(amount);
    }
}
