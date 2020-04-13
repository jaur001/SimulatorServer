package backend.model.simulables.bank.transactions;

import backend.model.bill.bills.ProductPurchase;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Transaction;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.provider.Provider;

public class ProductPurchaseTransaction extends Transaction {

    public ProductPurchaseTransaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        super(issuer, receiver, amount);
    }

    @Override
    protected boolean checkBill() {
        return issuer instanceof Provider && receiver instanceof Company;
    }

    @Override
    protected void generateBill() {
        if(checkBill())
            new CFDIBillGenerator().generateBill(new ProductPurchase((Provider) issuer,(Company) receiver));
        else System.out.println("Error generating the Bill, not found the elements of Product Purchase.");
    }


    @Override
    protected void pay() {
        if(checkBill()) makeInverseTransaction();
    }
}
