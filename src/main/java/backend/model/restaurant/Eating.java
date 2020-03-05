package backend.model.restaurant;

import backend.model.client.Client;
import backend.time.Time;

import java.util.Date;

public class Eating {
    private Restaurant restaurant;
    private Client client;
    private Date date;
    private Bill bill;
    private int invitedPeople;

    public Eating(Restaurant restaurant, Client client,Bill bill, int invitedPeople) {
        this.restaurant = restaurant;
        this.client = client;
        this.date = Time.getActualDate();
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
