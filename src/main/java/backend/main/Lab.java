package backend.main;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.implementations.database.SQLite.controllers.SQLiteTableCreator;
import backend.implementations.database.SQLite.controllers.SQLiteTableInsert;
import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.implementations.loaders.tripAvisor.TripAdvisorRestaurantReader;
import backend.model.NIFCreator.NIFCreator;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.bill.generator.XMLBill;
import backend.model.simulables.restaurant.Restaurant;
import backend.threadsInitializers.RestaurantThread;
import backend.utils.DatabaseUtils;
import backend.view.loaders.database.builder.builders.BillBuilder;
import backend.view.loaders.database.builder.builders.RestaurantBuilder;
import backend.view.loaders.database.controller.TableInsert;

import java.sql.SQLException;
import java.util.List;

public class Lab {

    public static void main(String[] args){
        restartBillTable();
    }

    private static void restartBillTable() {
        try {
            List<Restaurant> restaurantList = new RestaurantBuilder().buildList(new SQLiteTableSelector().read("Restaurant",1000000,1004000));
            new SQLiteTableCreator().dropTable("Restaurant");
            new SQLiteTableCreator().createTable(DatabaseUtils.getHeaders().get(0));
            new RestaurantNIFCreator().reset();
            restaurantList.forEach(restaurant -> restaurant.setNIF(new RestaurantNIFCreator().create()));
            TableInsert tableInsert = new SQLiteTableInsert();
            restaurantList.forEach(restaurant -> {
                try {
                    tableInsert.insert("Restaurant",new RestaurantBuilder().buildRow(restaurant));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            new SQLiteDatabaseConnector().disconnect();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
