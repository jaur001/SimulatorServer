package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.bill.Use;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.BillSettings;
import backend.utils.EuroFormatter;

public class EatingBill extends CFDIBill{
    private static final Type type = Type.income;
    private static final Use use = Use.G01;
    private Client client;
    private Restaurant restaurant;

    public EatingBill(Restaurant restaurant, Client client, double amount) {
        super(restaurant.getStreet(),type, use,restaurant.getName()
                ,restaurant.getNIF(),client.getName(),client.getNIF()
                , amount,BillSettings.getConcept(EatingBill.class.getSimpleName()));
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
        return this.getReceiverName() + " has gone to eat to " + this.getIssuerName()
                + ", amount: " + EuroFormatter.formatEuro(this.getTotal()) + ".";
    }
}
