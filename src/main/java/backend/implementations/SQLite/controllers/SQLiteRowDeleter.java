package backend.implementations.SQLite.controllers;

import backend.implementations.SQLite.DatabaseController;
import backend.view.loaders.database.controller.RowDeleter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteRowDeleter extends DatabaseController implements RowDeleter {

    private String headerName;

    @Override
    public void deleteAll(String headerName) throws SQLException, ClassNotFoundException {
        if (notExist(headerName)) return;
        init(headerName);
        PreparedStatement preparedStatement = getPreparedStatement(getSentence());
        preparedStatement.execute();
        preparedStatement.close();
    }

    private String getSentence() {
        return "Delete from " + actualHeaderName + ";";
    }
}
