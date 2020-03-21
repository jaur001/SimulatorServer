package backend.model.simulables.provider;

import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.financialData.ProviderFinancialData;
import backend.model.simulables.restaurant.Restaurant;
import backend.utils.RestaurantUtils;

import java.util.ArrayList;
import java.util.List;

public class Provider {
    private int NIF;
    private Product product;
    private String companyName;
    private String creationDate;
    private String ownerName;
    private String street;
    private String telephoneNumber;
    private ProviderFinancialData financialData;
    private double productPrice;
    private List<Restaurant> restaurantList = new ArrayList<>();


    public Provider(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        init(companyName,creationDate,ownerName,street,telephoneNumber);
        this.NIF = NIF;
    }

    public Provider(String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        init(companyName, creationDate, ownerName, street, telephoneNumber);
        NIF = new ProviderNIFCreator().create();
    }

    private void init(String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        this.companyName = companyName;
        this.creationDate = creationDate;
        this.ownerName = ownerName;
        this.street = street;
        this.telephoneNumber = telephoneNumber;
        this.financialData = new ProviderFinancialData(RestaurantUtils.intialSocialCapital);
        this.product = null;
        this.productPrice = 0;
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantList.add(restaurant);
        financialData.addMonthClient(productPrice);
    }

    public void removeRestaurant(Restaurant restaurant){
        if(restaurantList.contains(restaurant)){
            restaurantList.remove(restaurant);
            financialData.removeMonthClient(productPrice);
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

    public ProviderFinancialData getFinancialData() {
        return financialData;
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
}
