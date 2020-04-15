package backend.model.simulables.company;

import backend.model.simulables.Simulable;
import backend.model.simulables.bank.Payer;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.Simulator;

public abstract class Company implements Payer, Simulable{

    protected int NIF;
    protected String companyName;
    protected String street;
    protected String telephoneNumber;
    protected FinancialData financialData;
    protected static final double TAXES = 1000;

    public Company(int NIF, String companyName, String street, String telephoneNumber, FinancialData financialData) {
        this.NIF = NIF;
        this.companyName = companyName;
        this.street = street;
        this.telephoneNumber = telephoneNumber;
        this.financialData = financialData;
    }

    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getStreet() {
        return street;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public FinancialData getFinancialData() {
        return financialData;
    }

    protected abstract double getTaxes();

    protected void changePrice() {
        financialData.removeDebt(getTaxes());
        if(financialData.getLastMonthBenefits()>0)increasePrice();
        else decreasePrice();
        financialData.addDebt(getTaxes());
    }

    protected void declareBankruptcy() {
        Simulator.isGoingToClose(this);
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
    public void payTaxes(){
        financialData.pay(getTaxes());
    }

    @Override
    public String getMessage() {
        if(!Simulation.getCompanyListCopy().contains(this)) return "The company " + companyName + " has closed. NIF: " + getNIF();
        return "The company " + companyName + " has opened. NIF: " + getNIF();
    }
}
