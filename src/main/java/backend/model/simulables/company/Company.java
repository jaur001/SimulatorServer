package backend.model.simulables.company;

import backend.model.simulables.Simulable;
import backend.model.simulables.bank.Payer;
import backend.model.simulation.administration.centralControl.SimulationAdministrator;
import backend.utils.MathUtils;


public abstract class Company implements Payer, Simulable {

    protected int NIF;
    protected String companyName;
    protected String street;
    protected String telephoneNumber;
    protected FinancialData financialData;
    protected static double taxes = 0.1;

    public Company(int NIF, String companyName, String street, String telephoneNumber) {
        this.NIF = NIF;
        this.companyName = companyName;
        this.street = MathUtils.random(35000,40000)+"";
        this.telephoneNumber = telephoneNumber;
        this.financialData = getInitialFinancialData();
    }

    protected abstract FinancialData getInitialFinancialData();

    @Override
    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    @Override
    public String getName() {
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

    public abstract double getMortgage();

    public static double getTaxes() {
        return taxes;
    }

    public static void setTaxes(double taxes) {
        Company.taxes = taxes;
    }

    protected void checkFinances() {
        financialData.reset();
        changePrice();
        analyzeFinances();
    }

    protected void changePrice() {
        financialData.removeDebt(getMortgage());
        if(financialData.getLastMonthBenefits()>0)increasePrice();
        else decreasePrice();
        financialData.addDebt(getMortgage());
    }

    protected void analyzeFinances(){
        if(manageFinances())declareBankruptcy();
    }

    protected void declareBankruptcy() {
        SimulationAdministrator.isGoingToClose(this);
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
    public void payMortgage(){
        financialData.pay(getMortgage());
    }

    @Override
    public String[] getSimulable() {
        return new String[]{getNIF()+"",getName(),getStreet(),getTelephoneNumber()
                ,getFinancialData().getTreasury()+"",getFinancialData().getLastMonthBenefits()+""};
    }
}
