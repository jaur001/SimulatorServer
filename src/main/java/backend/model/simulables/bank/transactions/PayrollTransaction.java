package backend.model.simulables.bank.transactions;

import backend.model.bill.bills.Payroll;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Transaction;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.person.worker.Worker;

public class PayrollTransaction extends Transaction {

    public PayrollTransaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        super(issuer, receiver, amount);
    }

    @Override
    protected boolean checkBill() {
        return issuer instanceof ComplexCompany && receiver instanceof Worker;
    }

    @Override
    protected void generateBill() {
        if(checkBill())
            new CFDIBillGenerator().generateBill(new Payroll((ComplexCompany)issuer,(Worker)receiver));
        else System.out.println("Error generating the Bill, not found the elements of Payroll.");
    }

    @Override
    protected void pay() {
        if(checkBill()) makeNormalTransaction();
    }
}
