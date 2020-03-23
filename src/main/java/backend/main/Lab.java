package backend.main;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.implementations.database.SQLite.controllers.SQLiteTableCreator;
import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.bill.generator.XMLBill;
import backend.utils.DatabaseUtils;
import backend.view.loaders.database.builder.builders.BillBuilder;

import java.sql.SQLException;
import java.util.List;

public class Lab {

    public static void main(String[] args){
        restartBillTable();
    }

    private static void restartBillTable() {
        try {
            new SQLiteTableCreator().dropTable(DatabaseUtils.getHeaders().get(3));
            new SQLiteTableCreator().createTable(DatabaseUtils.getHeaders().get(3));
            new SQLiteDatabaseConnector().disconnect();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
