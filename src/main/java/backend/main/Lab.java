package backend.main;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.implementations.database.SQLite.controllers.SQLiteTableInsert;
import backend.implementations.loaders.CSV.CSVClientReader;
import backend.implementations.loaders.CSV.CSVProviderReader;
import backend.model.simulables.client.Client;
import backend.model.simulables.provider.Provider;
import backend.view.loaders.database.builder.builders.ClientBuilder;
import backend.view.loaders.database.builder.builders.ProviderBuilder;
import backend.view.loaders.database.controller.TableInsert;

import java.sql.SQLException;
import java.util.List;

public class Lab {

    public static void main(String[] args){
        List<Client> clientList = new CSVClientReader("C:/Users/PROPIETARIO/Desktop/HPDS/SimulatorServer/out/artifacts/RestaurantSimulator_war_exploded/CSVFiles/Clients.csv")
                .read(100000);
        List<Provider> providerList = new CSVProviderReader("C:/Users/PROPIETARIO/Desktop/HPDS/SimulatorServer/out/artifacts/RestaurantSimulator_war_exploded/CSVFiles/Providers.csv").read(99988);
        try {
            TableInsert tableInsert = new SQLiteTableInsert();
            clientList.forEach(client -> {
                try {
                    tableInsert.insert("Client",new ClientBuilder().buildRow(client));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            providerList.forEach(provider -> {
                try {
                    tableInsert.insert("Provider",new ProviderBuilder().buildRow(provider));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            new SQLiteDatabaseConnector().disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
