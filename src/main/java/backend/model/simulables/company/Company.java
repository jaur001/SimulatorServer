package backend.model.simulables.company;

import backend.model.simulables.Simulable;
import backend.model.simulables.bank.Payer;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.administration.Simulator;

import java.util.LinkedList;
import java.util.List;

public abstract class Company implements Payer, Simulable{

    protected int NIF;
    protected String companyName;
    protected String street;
    protected String telephoneNumber;
    protected FinancialData financialData;
    protected static final double TAXES = 1000;
    protected List<ServiceCompany> services;

    public Company(int NIF, String companyName, String street, String telephoneNumber, FinancialData financialData) {
        this.NIF = NIF;
        this.companyName = companyName;
        this.street = street.replaceAll("\"","");
        this.telephoneNumber = telephoneNumber;
        this.financialData = financialData;
        services = new LinkedList<>();
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

    public void addService(ServiceCompany serviceCompany) {
        services.add(serviceCompany);
        financialData.addDebt(serviceCompany.getPrice());
    }

    public void removeService(ServiceCompany serviceCompany) {
        services.remove(serviceCompany);
        financialData.removeDebt(serviceCompany.getPrice());
    }

    public boolean hasThisService(Service service){
        return services.stream()
                .anyMatch(serviceCompany -> serviceCompany.getProduct().equals(service));
    }

    public ServiceCompany getService(Service service){
        return services.stream()
                .filter(serviceCompany -> serviceCompany.getProduct().equals(service))
                .findFirst().orElse(null);
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
}
