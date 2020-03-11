package backend.model.restaurant;

import backend.model.client.Client;
import backend.model.time.Time;

import java.util.Date;

public class Eating {
    private Restaurant restaurant;
    private Client client;
    private Date date;
    private PlateBill plateBill;
    private int invitedPeople;

    public Eating(Restaurant restaurant, Client client, PlateBill plateBill, int invitedPeople) {
        this.restaurant = restaurant;
        this.client = client;
        this.date = Time.getActualDate();
        this.plateBill = plateBill;
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


    public PlateBill getPlateBill() {
        return plateBill;
    }

    public int getInvitedPeople() {
        return invitedPeople;
    }
}
