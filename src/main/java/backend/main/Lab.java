package backend.main;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.implementations.database.SQLite.controllers.SQLiteTableCreator;
import backend.implementations.database.SQLite.controllers.SQLiteTableDeleter;
import backend.implementations.database.SQLite.controllers.SQLiteTableInsert;
import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.simulables.person.client.Client;
import backend.utils.DatabaseUtils;
import backend.utils.MathUtils;
import backend.view.loaders.database.builder.builders.ClientBuilder;

import java.sql.SQLException;
import java.util.List;

public class Lab {

    public static void main(String[] args){
        SQLiteDatabaseConnector.setUrl("jdbc:sqlite:out/artifacts/RestaurantSimulator_war_exploded/Simulator.db");
        try {
            List<Client> clientList = new ClientBuilder().buildList(new SQLiteTableSelector().read("Person", PersonNIFCreator.getInitialValue(),PersonNIFCreator.getInitialValue()+100000));
            clientList.stream().filter(client -> !client.getTelephoneNumber().contains("-")).forEach(client -> System.out.println(client.getNIF()));
            new SQLiteDatabaseConnector().disconnect();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
