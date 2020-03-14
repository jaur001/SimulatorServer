package backend.server.commands;

import backend.implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.Simulation;
import backend.server.frontcontroller.FrontCommand;
import backend.model.simulation.TimeLine;

public class StartCommand extends FrontCommand {
    @Override
    public void process() {
        int clientCount = 1;
        int providerCount = 100;
        int restaurantCount = 30;
        String uriClients = context.getRealPath("/CSVFiles/Clients.csv");
        String uriProviders = context.getRealPath("/CSVFiles/Providers.csv");

        CFDIBillGenerator.setUriSales(context.getRealPath("/xmlFiles/EatingBills")+"/");
        CFDIBillGenerator.setUriPayrolls(context.getRealPath("/xmlFiles/Payrolls")+"/");
        SQLiteRestaurantReader.setSQLiteUrl("jdbc:sqlite:" + context.getRealPath("/Simulator.db"));


        Simulation.execute(new TimeLine(Simulation.init(providerCount,restaurantCount,clientCount,uriClients,uriProviders)));
        forward("/clients.jsp");
    }


}
