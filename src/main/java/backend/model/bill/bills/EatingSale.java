package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulables.client.Client;
import backend.model.simulables.restaurant.EatingBill;
import backend.model.simulables.restaurant.Restaurant;

public class EatingSale extends CFDIBill {
    private static final Type type = Type.income;

    public EatingSale(Restaurant restaurant, Client client, EatingBill eatingBill) {
        super(restaurant.getStreet(),type,restaurant.getName(),restaurant.getNIF(),client.getFullName(),client.getNIF(), eatingBill.getFinalPrice());
    }
}
