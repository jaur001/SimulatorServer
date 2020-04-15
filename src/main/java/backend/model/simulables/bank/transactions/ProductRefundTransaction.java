package backend.model.simulables.bank.transactions;

import backend.model.bill.bills.ProductRefund;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Transaction;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;

public class ProductRefundTransaction extends Transaction {

    public ProductRefundTransaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        super(issuer, receiver, amount);
    }

    @Override
    protected boolean checkBill() {
        return issuer instanceof Provider && receiver instanceof Company;
    }

    @Override
    protected void generateBill() {
        if(checkBill())
            new CFDIBillGenerator().generateBill(new ProductRefund((Provider) issuer,(Company) receiver));
        else System.out.println("Error generating the Bill, not found the elements of Product Refund.");
    }

    @Override
    protected void pay() {
        if(checkBill()) makeNormalTransaction();
    }
}
