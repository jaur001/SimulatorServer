package backend.main;

import backend.implementations.SQLite.SQLiteDatabaseConnector;
import backend.implementations.SQLite.controllers.SQLiteRowDeleter;
import backend.implementations.SQLite.controllers.SQLiteTableInsert;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.view.loaders.database.builder.builders.RestaurantBuilder;

import java.sql.SQLException;
import java.util.List;

public class Lab {

    public static void main(String[] args){
        try {
            List<Restaurant> restaurantLists = new RestaurantBuilder().buildList(new SQLiteTableSelector().read("Restaurant",1000000,1004600));
            new RestaurantNIFCreator().reset();
            restaurantLists.forEach(restaurant -> restaurant.setNIF(new RestaurantNIFCreator().create()));
            new SQLiteRowDeleter().deleteAll("Restaurant");
            restaurantLists.forEach(restaurant -> {
                try {
                    new SQLiteTableInsert().insert("Restaurant",new RestaurantBuilder().buildRow(restaurant));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            new SQLiteDatabaseConnector().disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
