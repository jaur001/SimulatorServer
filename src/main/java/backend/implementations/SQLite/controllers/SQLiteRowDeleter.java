package backend.implementations.SQLite.controllers;

import backend.implementations.SQLite.DatabaseController;
import backend.view.loaders.database.controller.RowDeleter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteRowDeleter extends DatabaseController implements RowDeleter {

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
