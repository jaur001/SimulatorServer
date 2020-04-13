package backend.model.simulables.company.provider;

import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.timeLine.TimeLine;

import java.util.ArrayList;
import java.util.List;

public class Provider extends Company{

    private Product product;
    private String creationDate;
    private String ownerName;
    private double productPrice;
    private List<Company> companyList = new ArrayList<>();

    public Provider(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(NIF,companyName,street,telephoneNumber,new FinancialData(RestaurantSettings.getInitialSocialCapital()));
        this.creationDate = creationDate;
        this.ownerName = ownerName;
        this.product = null;
        this.productPrice = 0;
        financialData.addDebt(getTaxes());
    }

    public Provider(String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        this(new ProviderNIFCreator().create(),companyName, creationDate, ownerName, street, telephoneNumber);
    }


    public void addClient(Company company){
        companyList.add(company);
        financialData.addIncome(productPrice);
    }

    public void removeClient(Company company){
        if(companyList.contains(company)){
            companyList.remove(company);
            financialData.removeIncome(productPrice);
        }
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Product getProduct() {
        return product;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductPrice(double productPrice) {
        financialData.removeDebt(getTaxes());
        this.productPrice = productPrice;
        financialData.addDebt(getTaxes());
    }

    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        if(TimeLine.isLastDay()) {
            checkFinances();
        }
    }

    public void checkFinances() {
        financialData.reset();
        changePrice();
        //analyzeFinances();
    }

    @Override
    protected boolean manageFinances() {
        return financialData.getTreasury() <= -5000;
    }

    @Override
    protected void increasePrice() {
        productPrice*= (1+ProviderSettings.PRICE_CHANGE);
    }

    @Override
    protected void decreasePrice() {
        productPrice*= (1-ProviderSettings.PRICE_CHANGE);
    }

    @Override
    protected void analyzeFinances() {

    }

    @Override
    protected double getTaxes() {
        return (productPrice*Company.TAXES)/100;
    }

    @Override
    public String getMessage() {
        if(!Simulation.getProviderListCopy().contains(this)) return "The Provider " + companyName + " has closed.";
        return "";
    }
}
