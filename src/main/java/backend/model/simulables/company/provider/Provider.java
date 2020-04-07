package backend.model.simulables.company.provider;

import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.timeLine.TimeLine;

import java.util.ArrayList;
import java.util.List;

public class Provider extends Company{
    private int NIF;
    private Product product;
    private String companyName;
    private String creationDate;
    private String ownerName;
    private String street;
    private String telephoneNumber;
    private double productPrice;
    private List<Restaurant> restaurantList = new ArrayList<>();


    public Provider(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(new FinancialData(RestaurantSettings.getInitialSocialCapital()));
        this.NIF = NIF;
        this.companyName = companyName;
        this.creationDate = creationDate;
        this.ownerName = ownerName;
        this.street = street;
        this.telephoneNumber = telephoneNumber;
        this.product = null;
        this.productPrice = 0;
    }

    public Provider(String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        this(new ProviderNIFCreator().create(),companyName, creationDate, ownerName, street, telephoneNumber);
    }


    public void addRestaurant(Restaurant restaurant){
        restaurantList.add(restaurant);
        financialData.addIncome(productPrice);
    }

    public void removeRestaurant(Restaurant restaurant){
        if(restaurantList.contains(restaurant)){
            restaurantList.remove(restaurant);
            financialData.removeIncome(productPrice);
        }
    }

    public int getNIF() {
        return NIF;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getStreet() {
        return street;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Product getProduct() {
        return product;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public void simulate() {
        if(TimeLine.isLastDay()) {
            changePrice();
        }
    }

    private void changePrice() {
        if(financialData.getIncome()> financialData.getLosses())increasePrice();
        else decreasePrice();
    }

    private void increasePrice() {
        productPrice*= (1+ProviderSettings.PRICE_CHANGE);
    }

    private void decreasePrice() {
        productPrice*= (1-ProviderSettings.PRICE_CHANGE);
    }
}
