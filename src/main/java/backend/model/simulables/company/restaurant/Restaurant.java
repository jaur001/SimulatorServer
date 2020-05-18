package backend.model.simulables.company.restaurant;

import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.company.complexCompanyWithStaff.ComplexWorkerWithStaff;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompanyWithStaff.Employer;
import backend.model.simulables.company.restaurant.administration.RestaurantEmployer;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.company.complexCompanyWithStaff.AdministratorWithStaff;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.timeLine.TimeLine;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.utils.MathUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Restaurant extends ComplexWorkerWithStaff {
    private PriceRange priceRange;
    private int tables;
    private AtomicInteger tablesAvailable;

    public Restaurant(String companyName, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        this(new RestaurantNIFCreator().create(), companyName, telephoneNumber, street, priceRange, tables);
    }

    public Restaurant(int NIF, String companyName, String telephoneNumber, String street, PriceRange priceRange, int tables) {
        super(NIF,companyName,street,telephoneNumber,new FinancialData( RestaurantSettings.getInitialSocialCapital()));
        this.priceRange = priceRange;
        this.tables = tables;
        financialData.addDebt(getMortgage());
        this.tablesAvailable = new AtomicInteger((int)(tables* RestaurantSettings.EATINGS_PER_TABLE*RestaurantSettings.getCapacity()));
        initAdministration();
    }

    public double getScore(){
        return administratorWithStaff.getWorkerList().stream()
                .map(worker -> (double)worker.getQuality().getScore())
                .reduce(0.0,Double::sum)/ administratorWithStaff.getWorkerList().size();
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
        return administratorWithStaff.getContractsSize();
    }

    public List<Worker> getWorkers(){
        return administratorWithStaff.getWorkerList();
    }

    public List<Provider> getProviders(){
        return administratorWithStaff.getProvidersList();
    }

    public AdministratorWithStaff getAdministrator() {
        return administratorWithStaff;
    }

    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        if(hasNotThisService(Service.Cleaning)) searcher.searchBetterService(Service.Cleaning);
        checkProducts();
        checkContracts();
        restartTablesAvailable();
        if(TimeLine.isLastDay()) {
            checkFinances();
        }
    }


    private void checkProducts() {
        if(getProviders().size()< Product.values().length) searcher.addMissingProvider();
        if(getProviders().size()> Product.values().length) searcher.removeUnnecessaryProviders();
        administratorWithStaff.checkProducts();
    }

    public void checkContracts() {
        employer.checkStaff();
        employer.checkExpiredSoonContracts();
        employer.checkExpiredContracts();
    }

    private void restartTablesAvailable() {
        tablesAvailable.set(tables*RestaurantSettings.EATINGS_PER_TABLE);
    }


    @Override
    protected Employer initEmployer() {
        return new RestaurantEmployer(administratorWithStaff,tables);
    }

    @Override
    protected boolean manageFinances() {
        return administratorWithStaff.manageFinances();
    }

    @Override
    protected void searchBetterProviders() {
        searcher.searchBetterProviders();
    }

    @Override
    protected void searchBetterNeededServices() {
        searcher.searchBetterService(Service.Cleaning);
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
    public double getMortgage() {
        return (MathUtils.twoNumberMean(getMaxPricePlate(),getMinPricePlate())* ComplexCompany.TAXES)/10;
    }

    @Override
    protected void increasePrice() {
        priceRange.increasePrice(RestaurantSettings.getPriceChange());
    }

    @Override
    protected void decreasePrice() {
        priceRange.decreasePrice(RestaurantSettings.getPriceChange());
    }
}
