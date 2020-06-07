package backend.model.simulables.bank.transactions;

import backend.model.bill.bills.ServiceBill;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Transaction;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.ServiceCompany;

public class ServiceBillTransaction extends Transaction {

    public ServiceBillTransaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        super(issuer, receiver, amount);
    }

    @Override
    protected boolean checkBill() {
        return issuer instanceof ServiceCompany && receiver instanceof ComplexCompany;
    }

    @Override
    protected double generateBill() {
        if(checkBill()) {
            ServiceBill bill = new ServiceBill((ServiceCompany) issuer, (ComplexCompany) receiver);
            new CFDIBillGenerator().generateBill(bill);
            return bill.getTaxRate();
        } else System.out.println("Error generating the Bill, not found the elements of Service bill.");
        return 0;
    }

    @Override
    protected void pay() {
        if(checkBill()) makeInverseTransaction();
    }
}
