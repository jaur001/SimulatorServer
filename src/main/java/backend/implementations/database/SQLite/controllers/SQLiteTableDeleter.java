package backend.implementations.database.SQLite.controllers;

import backend.implementations.database.SQLite.DatabaseController;
import backend.view.loaders.database.controller.TableDeleter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteTableDeleter extends DatabaseController implements TableDeleter {

    private String headerName;

    @Override
    public void deleteAll(String headerName) throws SQLException, ClassNotFoundException {
        if (checkTable(headerName)) return;
        init(headerName);
        PreparedStatement preparedStatement = prepareDelete();
        preparedStatement.execute();
        preparedStatement.close();
    }


    private PreparedStatement prepareDelete() throws SQLException {
        return connection.prepareStatement(getSentence());
    }

    private String getSentence() {
        return "Delete from " + actualHeaderName + ";";
    }
}
