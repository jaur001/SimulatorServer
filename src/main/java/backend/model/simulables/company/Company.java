package backend.model.simulables.company;

import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Payer;

public abstract class Company extends EventGenerator implements EconomicAgent, Payer, Simulable {

    protected FinancialData financialData;
    protected static final double TAXES = 1000;

    public Company(FinancialData financialData) {
        this.financialData = financialData;
    }

    protected abstract double getTaxes();

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
        financialData.pay(getTaxes());
    }
}
