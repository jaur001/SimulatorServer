package backend.model.simulables.bank.transactions;

import backend.model.bill.bills.ProductRefund;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Transaction;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;

public class ProductRefundTransaction extends Transaction {

    public ProductRefundTransaction(EconomicAgent issuer, EconomicAgent receiver, double amount) {
        super(issuer, receiver, amount);
    }

    @Override
    protected boolean checkBill() {
        return issuer instanceof Provider && receiver instanceof ComplexCompany;
    }

    @Override
    protected double generateBill() {
        if(checkBill()) {
            ProductRefund bill = new ProductRefund((Provider) issuer, (ComplexCompany) receiver);
            new CFDIBillGenerator().generateBill(bill);
            return bill.getTaxRate();
        } else System.out.println("Error generating the Bill, not found the elements of Product Refund.");
        return 0;
    }

    @Override
    protected void pay() {
        if(checkBill()) makeNormalTransaction();
    }
}
