package backend.main;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.implementations.database.SQLite.controllers.SQLiteTableCreator;
import backend.utils.DatabaseUtils;
import java.sql.SQLException;

public class Lab {

    public static void main(String[] args){
        try {
            new SQLiteTableCreator().dropTable(DatabaseUtils.getHeaders().get(3));
            new SQLiteTableCreator().createTable(DatabaseUtils.getHeaders().get(3));
            new SQLiteDatabaseConnector().disconnect();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
