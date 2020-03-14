package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulables.client.Client;
import backend.model.simulables.restaurant.Bill;
import backend.model.simulables.restaurant.Restaurant;

import java.util.Date;

public class EatingSale extends CFDIBill {
    private Restaurant restaurant;
    private Client client;
    private Bill bill;
    private int invitedPeople;
    private static final Type type = Type.income;

    public EatingSale(Restaurant restaurant, Client client, Bill bill, int invitedPeople) {
        super(restaurant.getStreet(),type,restaurant.getName(),restaurant.getNIF(),client.getFullName(),client.getNIF(),bill.getFinalPrice());
        this.restaurant = restaurant;
        this.client = client;
        this.bill = bill;
        this.invitedPeople = invitedPeople;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Client getClient() {
        return client;
    }

    public Date getDate() {
        return date;
    }


    public Bill getBill() {
        return bill;
    }

    public int getInvitedPeople() {
        return invitedPeople;
    }
}
