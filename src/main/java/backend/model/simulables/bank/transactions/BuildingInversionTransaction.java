package backend.model.simulables.bank.transactions;

import backend.model.bill.bills.BuildingInversion;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Transaction;
import backend.model.simulables.company.ComplexCompany;

public class BuildingInversionTransaction extends Transaction {

    public BuildingInversionTransaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        super(issuer, receiver, amount);
    }

    @Override
    protected boolean checkBill() {
        return issuer instanceof Bank && receiver instanceof ComplexCompany;
    }

    @Override
    protected void generateBill() {
        if(checkBill())new CFDIBillGenerator().generateBill(new BuildingInversion((Bank) issuer,(ComplexCompany) receiver,amount));
        else System.out.println("Error generating the Bill, not found the elements of Eating bill.");
    }

    @Override
    protected void pay() {
        if(checkBill()) makeInverseTransaction();
    }
}
