package backend.model.simulables.bank.transactions;

import backend.model.bill.bills.EatingSale;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Transaction;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.Client;

public class EatingSaleTransaction extends Transaction {

    public EatingSaleTransaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        super(issuer, receiver, amount);
    }

    @Override
    protected void generateBill() {
        if(checkBill())
            new CFDIBillGenerator().generateBill(new EatingSale((Restaurant)issuer,(Client)receiver,amount));
        else System.out.println("Error generating the Bill, not found the elements of Eating Sale.");
    }

    @Override
    protected boolean checkBill() {
        return issuer instanceof Restaurant && receiver instanceof Client;
    }

    @Override
    protected void pay() {
        if(checkBill()) makeInverseTransaction();
    }
}
