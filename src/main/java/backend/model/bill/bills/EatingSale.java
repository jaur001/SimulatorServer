package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulables.client.Client;
import backend.model.simulables.restaurant.Bill;
import backend.model.simulables.restaurant.Restaurant;

import java.util.Date;

public class EatingSale extends CFDIBill {
    private static final Type type = Type.income;

    public EatingSale(Restaurant restaurant, Client client, Bill bill) {
        super(restaurant.getStreet(),type,restaurant.getName(),restaurant.getNIF(),client.getFullName(),client.getNIF(),bill.getFinalPrice());
    }
}
