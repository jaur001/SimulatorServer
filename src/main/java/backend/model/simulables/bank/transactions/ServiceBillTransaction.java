package backend.model.simulables.bank.transactions;

import backend.model.bill.bills.ServiceBill;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Transaction;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;

public class ServiceBillTransaction extends Transaction {

    public ServiceBillTransaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        super(issuer, receiver, amount);
    }

    @Override
    protected boolean checkBill() {
        return issuer instanceof ServiceCompany && receiver instanceof Company;
    }

    @Override
    protected void generateBill() {
        if(checkBill())
            new CFDIBillGenerator().generateBill(new ServiceBill((ServiceCompany)issuer,(Company) receiver));
        else System.out.println("Error generating the Bill, not found the elements of Service bill.");
    }

    @Override
    protected void pay() {
        if(checkBill()) makeInverseTransaction();
    }
}
