package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulables.provider.Provider;
import backend.model.simulables.restaurant.Restaurant;

public class ProductPurchase extends CFDIBill {

    private static final Type type = Type.income;
    private Provider provider;
    private Restaurant restaurant;

    public ProductPurchase(Provider provider, Restaurant restaurant) {
        super(restaurant.getStreet(), type, provider.getCompanyName(),provider.getNIF(), restaurant.getName(), restaurant.getNIF(), provider.getProductPrice());
        this.provider = provider;
        this.restaurant = restaurant;
    }
}
