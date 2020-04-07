package backend.model.simulables.company.restaurant;

import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulables.Simulable;
import backend.model.simulation.timeLine.TimeLine;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.utils.MathUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// No se tiene en cuenta que el plato puede acabarse ni que el plato use productos de un proveedor.
// Simplemente se trata al proveedor como una renta mensual que tiene que pagar.

public class Restaurant extends Company{
    private int NIF;
    private String name;
    private String street;
    private String telephoneNumber;
    private PriceRange priceRange;
    private int tables;
    private AtomicInteger tablesAvailable;
    private static final int eatingsPerTable = 6;

    private Administrator administrator;


    public Restaurant(int NIF, String name, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        super(new FinancialData( RestaurantSettings.getInitialSocialCapital()));
        this.NIF = NIF;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.street = street;
        this.priceRange = priceRange;
        this.tables = tables;
        this.tablesAvailable = new AtomicInteger(tables*eatingsPerTable);
        this.administrator = new Administrator(financialData,this);
    }

    public Restaurant(String name, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        this(new RestaurantNIFCreator().create(),name, telephoneNumber, street, priceRange, tables);
    }


    public void addProvider(Provider provider){
        administrator.addProvider(provider);
    }


    public void addWorker(Worker worker, double salary){
        administrator.addWorker(worker,salary);
    }


    public double getScore(){
        return administrator.getWorkerList().stream()
                .map(worker -> (double)worker.getQuality().getScore())
                .reduce(0.0,Double::sum)/administrator.getWorkerList().size();
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
        return administrator.getWorkerList().size();
    }

    @Override
    public void simulate() {
        if(TimeLine.isLastDay()) {
            payWorkers();
            payProviders();
        }
        administrator.checkExpiredSoonContracts();
        administrator.checkExpiredContracts();
        restartTablesAvailable();
    }

    public void payWorkers() {
        administrator.getWorkerList()
                .forEach(worker -> addEvent(administrator.payWorker(worker)));
    }

    private void payProviders() {
        administrator.getProvidersList()
                .forEach(provider ->addEvent(administrator.payProvider(provider)));
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
