package backend.model.simulables.bank.transactions;

import backend.model.bill.bills.BuildingInversion;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Transaction;
import backend.model.simulables.company.complexCompany.ComplexCompany;

public class BuildingInversionTransaction extends Transaction {

    public BuildingInversionTransaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        super(issuer, receiver, amount);
    }

    @Override
    protected boolean checkBill() {
        return issuer instanceof Bank && receiver instanceof ComplexCompany;
    }

    @Override
    protected double generateBill() {
        if(checkBill()) {
            BuildingInversion bill = new BuildingInversion((Bank) issuer, (ComplexCompany) receiver, amount);
            new CFDIBillGenerator().generateBill(bill);
            return bill.getTaxRate();
        } else System.out.println("Error generating the Bill, not found the elements of Eating bill.");
        return 0;
    }

    @Override
    protected void pay() {
        if(checkBill()) makeInverseTransaction();
    }
}
