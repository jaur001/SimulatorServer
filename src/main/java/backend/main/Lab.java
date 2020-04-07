package backend.main;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.implementations.database.SQLite.controllers.SQLiteTableCreator;
import backend.implementations.database.SQLite.controllers.SQLiteTableDeleter;
import backend.implementations.database.SQLite.controllers.SQLiteTableInsert;
import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.simulables.person.client.Client;
import backend.model.simulation.Simulator;
import backend.utils.DatabaseUtils;
import backend.utils.MathUtils;
import backend.view.loaders.database.builder.builders.ClientBuilder;

import java.sql.SQLException;
import java.util.List;

public class Lab {

    public static void main(String[] args){
    }

}
