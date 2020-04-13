package backend.model.bill.generator;

import backend.implementations.SQLite.controllers.SQLiteTableInsert;
import backend.model.bill.CFDIBill;
import backend.view.loaders.database.builder.builders.BillBuilder;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BillDatabase {

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();;

    public static void saveInDatabase(CFDIBill bill, String filePath, String fileName) {
        executor.submit(() -> {
            try {
                new SQLiteTableInsert().insert("Bill", new BillBuilder().buildRow(new XMLBill(bill, filePath,fileName)));
            } catch (SQLException | ClassNotFoundException e) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    saveInDatabase(bill, filePath, fileName);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
