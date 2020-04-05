package backend.model.simulables.company;

import backend.model.event.EventGenerator;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Payer;

public abstract class Company extends EventGenerator implements EconomicAgent, Payer {

    protected static final double companyTaxes = 1000;
    protected FinancialData financialData;

    public Company(FinancialData financialData) {
        this.financialData = financialData;
        financialData.addDebt(companyTaxes);
    }

    public FinancialData getFinancialData() {
        return financialData;
    }

    @Override
    public void pay(double amount) {
        financialData.pay(amount);
    }

    @Override
    public void collect(double amount) {
        financialData.collect(amount);
    }

    @Override
    public void pay(){
        financialData.pay(companyTaxes);
    }
}
