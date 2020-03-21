package backend.model.simulables.restaurant;

import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.bill.bills.ProductPurchase;
import backend.model.financialData.RestaurantFinancialData;
import backend.model.simulables.client.Client;
import backend.model.simulables.provider.Provider;
import backend.model.bill.bills.Payroll;
import backend.model.simulables.restaurant.worker.Worker;
import backend.model.simulables.Simulable;
import backend.model.simulation.TimeLine;
import backend.utils.MathUtils;
import backend.utils.RestaurantUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// No se tiene en cuenta que el plato puede acabarse ni que el plato use productos de un proveedor.
// Simplemente se trata al proveedor como una renta mensual que tiene que pagar.

public class Restaurant implements Simulable {
    private int NIF;
    private String name;
    private String street;
    private String telephoneNumber;
    private PriceRange priceRange;
    private int tables;
    private AtomicInteger tablesAvailable;
    private static final int eatingsPerTable = 6;

    private List<Worker> workerList;
    private RestaurantFinancialData financialData;
    private List<Provider> providersList;


    public Restaurant(int NIF, String name, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        this(name, telephoneNumber, street, priceRange, tables, RestaurantUtils.intialSocialCapital);
        this.NIF = NIF;
    }


    public Restaurant(String name, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        this(name, telephoneNumber, street, priceRange, tables, RestaurantUtils.intialSocialCapital);
        this.NIF = new RestaurantNIFCreator().create();
    }

    public Restaurant(String name, String telephoneNumber, String street, PriceRange priceRange, int tables, double socialCapital) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.street = street;
        this.priceRange = priceRange;
        this.tables = tables;
        this.tablesAvailable = new AtomicInteger(tables*eatingsPerTable);
        this.workerList = new ArrayList<>();
        this.providersList = new ArrayList<>();
        this.financialData = new RestaurantFinancialData(socialCapital);
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
    }

    public void removeWorker(Worker worker){
        if(workerList.contains(worker)){
            workerList.remove(worker);
            financialData.removeDebt(worker.getSalary());
        }
    }

    public void payEating(double amount){
        financialData.addSale(amount);
    }

    public void payDebts() {
        System.out.println(this.name +" payed Debts:");
        financialData.payDebts();
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

    public RestaurantFinancialData getFinancialData() {
        return financialData;
    }



    @Override
    public void simulate() {
        if(TimeLine.isLastDay()) {
            this.payDebts();
            workerList.forEach(worker -> new CFDIBillGenerator().generateBill(new Payroll(worker, this)));
            providersList.forEach(provider -> new CFDIBillGenerator().generateBill(new ProductPurchase(provider,this)));
        }
        restartTablesAvailable();
    }

    private void restartTablesAvailable() {
        tablesAvailable.set(tables*eatingsPerTable);
    }

    public boolean acceptClient(Client client) {
        int tablesNeeded = (client.getPeopleInvited()+1)/4 + (client.getPeopleInvited()+1)%4==0? 0 : 1;
        if(tablesAvailable.get()-tablesNeeded >= 0){
            tablesAvailable.set(tablesAvailable.get()-tablesNeeded);
            return true;
        }
        return false;
    }
}
