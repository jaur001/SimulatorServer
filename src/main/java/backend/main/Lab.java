package backend.main;


import backend.implementations.SQLite.controllers.SQLiteTableCreator;
import backend.view.loaders.database.DatabaseManager;

import java.sql.SQLException;

public class Lab {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new SQLiteTableCreator().createTable(DatabaseManager.getHeaders().get(4));
    }

}
