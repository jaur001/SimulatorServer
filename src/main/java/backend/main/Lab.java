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
            new SQLiteDatabaseConnector().disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
