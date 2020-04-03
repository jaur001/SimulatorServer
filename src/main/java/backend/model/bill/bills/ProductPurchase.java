package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.BillSettings;

public class ProductPurchase extends CFDIBill {

    private static final Type type = Type.income;
    private Provider provider;
    private Restaurant restaurant;


    public ProductPurchase(Provider provider, Restaurant restaurant) {
        super(restaurant.getStreet(), type, provider.getCompanyName(),provider.getNIF(), restaurant.getName(), restaurant.getNIF(), provider.getProductPrice(), BillSettings.getConcept("ProductPurchase"));
        this.restaurant = restaurant;
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
