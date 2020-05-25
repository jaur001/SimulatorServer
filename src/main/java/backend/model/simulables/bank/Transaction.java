package backend.model.simulables.bank;

import backend.utils.MathUtils;

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
        taxRate = generateBill();
        if(taxRate != 0)pay();
    }

    protected abstract boolean checkBill();

    protected abstract void pay();

    protected abstract double generateBill();

    protected void makeNormalTransaction(){
        issuer.pay(amount*taxRate);
        receiver.collect(amount*taxRate);
    }

    protected void makeInverseTransaction(){
        receiver.pay(amount*taxRate);
        issuer.collect(amount*taxRate);
    }
}
