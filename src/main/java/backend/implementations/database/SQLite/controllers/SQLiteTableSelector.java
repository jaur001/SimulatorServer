package backend.implementations.database.SQLite.controllers;

import backend.implementations.database.SQLite.DatabaseController;
import backend.view.loaders.database.controller.TableSelector;
import backend.view.loaders.database.elements.Row;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SQLiteTableSelector extends DatabaseController implements TableSelector {
    private int fromID;
    private int toID;

    @Override
    public List<Row> read(String headerName,int fromID, int toID) throws SQLException, ClassNotFoundException {
        if(notContains(headerName)){
            System.out.println("Error: Table does not exist");
            return new LinkedList<>();
        }
        this.fromID = fromID;
        this.toID = toID;
        init(headerName);
        ResultSet resultSet = getResultSet();
        return getRows(resultSet);
    }

    private ResultSet getResultSet() throws SQLException {
        return connection.prepareStatement(getSelect()).executeQuery();
    }

    private String getSelect() {
        return "select * from " + actualHeaderName + " where "
                + getActualHeader(actualHeaderName).getPrimaryKeyName()
                + " between " + fromID + " and " + toID + ";";
    }

    private List<Row> getRows(ResultSet resultSet) throws SQLException {
        List<Row> rows = new LinkedList<>();
        while (resultSet.next()){
            rows.add(new SQLiteDataReader().readData(resultSet));
        }
        return rows;
    }
}
