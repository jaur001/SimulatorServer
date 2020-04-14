package backend.model.simulables.company.restaurant;

import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.provider.Product;
import backend.model.simulables.company.restaurant.administration.Administrator;
import backend.model.simulables.company.restaurant.administration.Employer;
import backend.model.simulables.company.restaurant.administration.OfferManager;
import backend.model.simulables.company.restaurant.administration.ProviderSearcher;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.timeLine.TimeLine;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.utils.MathUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// Simplemente se trata al proveedor como una renta mensual que tiene que pagar.

public class Restaurant extends Company{
    private PriceRange priceRange;
    private int tables;
    private AtomicInteger tablesAvailable;

    private Employer employer;
    private Administrator administrator;
    private ProviderSearcher searcher;

    public Restaurant(String companyName, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        this(new RestaurantNIFCreator().create(), companyName, telephoneNumber, street, priceRange, tables);
    }

    public Restaurant(int NIF, String companyName, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        super(NIF,companyName,street,telephoneNumber,new FinancialData( RestaurantSettings.getInitialSocialCapital()));
        this.priceRange = priceRange;
        this.tables = tables;
        financialData.addDebt(getTaxes());
        initAdministration(tables);
    }

    public void initAdministration(int tables) {
        this.tablesAvailable = new AtomicInteger(tables*RestaurantSettings.EATINGS_PER_TABLE);
        this.administrator = new Administrator(financialData,tables,this);
        this.employer = new Employer(administrator,new OfferManager(this,administrator));
        this.searcher = new ProviderSearcher(administrator);
    }

    public double getScore(){
        return administrator.getWorkerList().stream()
                .map(worker -> (double)worker.getQuality().getScore())
                .reduce(0.0,Double::sum)/ administrator.getWorkerList().size();
    }

    public void collectEating(double amount){
        financialData.addSale(amount);
    }

    public double getPricePlateMean(){
        return MathUtils.twoNumberMean(this.getMinPricePlate(),this.getMaxPricePlate());
    }

    public double getMinPricePlate() {
        return priceRange.getMinPrice();
    }

    public double getMaxPricePlate() {
        return priceRange.getMaxPrice();
    }

    public int getTables() {
        return tables;
    }

    public int getNumberOfWorkers(){
        return administrator.getContractsSize();
    }

    public List<Worker> getWorkers(){
        return administrator.getWorkerList();
    }

    public List<Provider> getProviders(){
        return administrator.getProvidersList();
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        if(TimeLine.isLastDay()) {
            payAndCheckDebts();
            analyzeFinances();
        }
        checkProducts();
        checkContracts();
        restartTablesAvailable();
    }

    private void checkProducts() {
        if(getProviders().size()< Product.values().length) searcher.addMissingProvider();
        if(getProviders().size()> Product.values().length) searcher.removeUnnecessaryProviders();
        administrator.checkProducts();
    }

    public void checkContracts() {
        employer.checkStaff();
        employer.checkExpiredSoonContracts();
        employer.checkExpiredContracts();
    }

    private void restartTablesAvailable() {
        tablesAvailable.set(tables*RestaurantSettings.EATINGS_PER_TABLE);
    }

    public void payAndCheckDebts() {
        administrator.payDebts();
        changePrice();
        checkBetterProviders();
    }

    @Override
    protected boolean manageFinances() {
        return administrator.manageFinances();
    }

    private void checkBetterProviders() {
        searcher.searchBetterOptions();
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
    protected double getTaxes() {
        return (MathUtils.twoNumberMean(getMaxPricePlate(),getMinPricePlate())*Company.TAXES)/10;
    }

    @Override
    protected void increasePrice() {
        priceRange.increasePrice(RestaurantSettings.PRICE_CHANGE);
    }

    @Override
    protected void decreasePrice() {
        priceRange.decreasePrice(RestaurantSettings.PRICE_CHANGE);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
