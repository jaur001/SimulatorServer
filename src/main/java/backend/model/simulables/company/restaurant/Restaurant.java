package backend.model.simulables.company.restaurant;

import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.bill.bills.ProductPurchase;
import backend.model.event.Event;
import backend.model.event.EventFactory;
import backend.model.event.events.PayrollEvent;
import backend.model.event.events.ProductPurchaseEvent;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.company.provider.Provider;
import backend.model.bill.bills.Payroll;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulables.Simulable;
import backend.model.simulation.TimeLine;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.utils.MathUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// No se tiene en cuenta que el plato puede acabarse ni que el plato use productos de un proveedor.
// Simplemente se trata al proveedor como una renta mensual que tiene que pagar.

public class Restaurant extends Company implements Simulable, EventFactory{
    private int NIF;
    private String name;
    private String street;
    private String telephoneNumber;
    private PriceRange priceRange;
    private int tables;
    private AtomicInteger tablesAvailable;
    private static final int eatingsPerTable = 6;

    private List<Worker> workerList;
    private List<Provider> providersList;


    public Restaurant(int NIF, String name, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        super(new FinancialData( RestaurantSettings.getInitialSocialCapital()));
        this.NIF = NIF;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.street = street;
        this.priceRange = priceRange;
        this.tables = tables;
        this.tablesAvailable = new AtomicInteger(tables*eatingsPerTable);
        this.workerList = new ArrayList<>();
        this.providersList = new ArrayList<>();
    }

    public Restaurant(String name, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        this(new RestaurantNIFCreator().create(),name, telephoneNumber, street, priceRange, tables);
    }


    public void addProvider(Provider provider){
        providersList.add(provider);
        financialData.addDebt(provider.getProductPrice());
    }

    public void removeProvider(Provider provider){
        if(providersList.contains(provider)){
            providersList.remove(provider);
            financialData.removeDebt(provider.getProductPrice());
        }
    }

    public void addWorker(Worker worker){
        workerList.add(worker);
        financialData.addDebt(worker.getSalary());
        worker.hire(this,RestaurantSettings.getSalary(Job.valueOf(worker.getJob())));
    }

    public void removeWorker(Worker worker){
        if(workerList.contains(worker)){
            workerList.remove(worker);
            financialData.removeDebt(worker.getSalary());
            worker.fire();
        }
    }

    public void collectEating(double amount){
        financialData.addSale(amount);
    }

    public double getPricePlateMean(){
        return MathUtils.twoNumberMean(this.getMinPricePlate(),this.getMaxPricePlate());
    }

    public int getNIF() {
        return NIF;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public int getMinPricePlate() {
        return priceRange.getMinPrice();
    }

    public int getMaxPricePlate() {
        return priceRange.getMaxPrice();
    }

    public int getTables() {
        return tables;
    }

    public int getNumberOfWorkers(){
        return workerList.size();
    }

    @Override
    public void simulate() {
        if(TimeLine.isLastDay()) {
            payWorkers();
            payProviders();
        }
        restartTablesAvailable();
    }

    private void restartTablesAvailable() {
        tablesAvailable.set(tables*eatingsPerTable);
    }

    public void payProviders() {
        providersList.forEach(this::payProvider);
    }

    public void payProvider(Provider provider) {
        new CFDIBillGenerator().generateBill(new ProductPurchase(provider,this));
        Bank.makeTransaction(this,provider,provider.getProductPrice());
    }

    public void payWorkers() {
        workerList.forEach(this::payWorker);
    }

    public void payWorker(Worker worker) {
        Payroll payroll= new Payroll(worker, this);
        new CFDIBillGenerator().generateBill(payroll);
        EventFactory.addEvent(buildEvent(payroll));
        Bank.makeTransaction(this,worker,worker.getSalary());
    }

    public boolean acceptClient(Client client) {
        int tablesNeeded = (client.getPeopleInvited()+1)/4 + (client.getPeopleInvited()+1)%4==0? 0 : 1;
        if(tablesAvailable.get()-tablesNeeded >= 0){
            tablesAvailable.set(tablesAvailable.get()-tablesNeeded);
            return true;
        }
        return false;
    }

    @Override
    public Event buildEvent(Object data) {
        if(data instanceof Payroll)return new PayrollEvent((Payroll)data);
        return new ProductPurchaseEvent((ProductPurchase)data);
    }
}
