package backend.model.simulables.provider;

import backend.model.NIFCreator.CompanyNIFCreator;
import backend.model.financialData.ProviderFinancialData;
import backend.model.simulables.restaurant.Restaurant;

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
    private List<Restaurant> restaurantList;



    public Provider(String[] data, double socialCapital) {
        NIF = new CompanyNIFCreator().create();
        this.product = null;
        this.companyName = data[0];
        this.creationDate = data[1];
        this.ownerName = data[2];
        this.street = data[3];
        this.telephoneNumber = data[4];
        this.financialData = new ProviderFinancialData(socialCapital);
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
