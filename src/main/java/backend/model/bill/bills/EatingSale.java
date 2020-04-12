package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.BillSettings;

public class EatingSale extends CFDIBill{
    private static final Type type = Type.income;
    private Client client;
    private Restaurant restaurant;

    public EatingSale(Restaurant restaurant, Client client, double amount) {
        super(restaurant.getStreet(),type,restaurant.getCompanyName(),restaurant.getNIF(),client.getFullName(),client.getNIF(), amount,BillSettings.getConcept("EatingSale"));
        this.client = client;
        this.restaurant = restaurant;
        restaurant.collectEating(amount);
    }

    public Client getClient() {
        return client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public String getMessage() {
        return this.getReceiverName() + " has gone to eat to " + this.getIssuerName() + ", amount: " + this.getTotal();
    }
}
