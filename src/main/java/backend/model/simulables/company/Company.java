package backend.model.simulables.company;

import backend.model.event.Event;
import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.Payer;
import backend.model.simulation.Simulator;
import backend.model.simulation.settings.settingsList.ProviderSettings;

public abstract class Company extends EventGenerator implements EconomicAgent, Payer, Simulable, Event {

    protected FinancialData financialData;
    protected static final double TAXES = 1000;

    public Company(FinancialData financialData) {
        this.financialData = financialData;
    }

    public FinancialData getFinancialData() {
        return financialData;
    }

    protected abstract double getTaxes();

    protected void changePrice() {
        if(financialData.getLastMonthBenefits()>0)increasePrice();
        else decreasePrice();
    }

    protected void declareBankruptcy() {
        Simulator.closeCompany(this);
    }

    protected void analyzeFinances(){
        if(manageFinances())declareBankruptcy();
    }

    protected abstract boolean manageFinances();

    protected abstract void increasePrice();

    protected abstract void decreasePrice();

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
