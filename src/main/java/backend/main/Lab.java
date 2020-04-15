package backend.main;

import backend.implementations.SQLite.SQLiteDatabaseConnector;
import backend.implementations.SQLite.controllers.SQLiteRowDeleter;
import backend.implementations.SQLite.controllers.SQLiteTableInsert;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.bill.CFDIBill;
import backend.model.bill.bills.Payroll;
import backend.model.bill.generator.XMLBill;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.view.loaders.database.builder.builders.RestaurantBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Lab {

    public static void main(String[] args){
    }

}
