package backend.view.bills;

import backend.model.provider.Provider;
import backend.model.restaurant.Restaurant;

public interface PurchaseGenerator {

    void generatePurchase(Restaurant restaurant, Provider provider);
}
