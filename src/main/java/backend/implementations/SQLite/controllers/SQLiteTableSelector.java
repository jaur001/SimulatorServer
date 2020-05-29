package backend.implementations.SQLite.controllers;

import backend.implementations.SQLite.DatabaseController;
import backend.implementations.SQLite.SQLiteDataReader;
import backend.view.loaders.database.DatabaseManager;
import backend.view.loaders.database.controller.TableSelector;
import backend.view.loaders.database.elements.Row;
import backend.view.loaders.database.elements.Selector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SQLiteTableSelector extends DatabaseController implements TableSelector {
    private int fromID;
    private int toID;

    @Override
    public List<Row> read(String headerName, int page) throws SQLException, ClassNotFoundException {
        init(headerName);
        int fromID = getActualHeader().getInitialPrimaryKeyValue()+(page-1)* DatabaseManager.getPageLength();
        return read(headerName,fromID,fromID+ DatabaseManager.getPageLength());
    }

    @Override
    public List<Row> read(String headerName, int page, Selector...selectors) throws SQLException, ClassNotFoundException {
        init(headerName);
        int fromID = getActualHeader().getInitialPrimaryKeyValue()+(page-1)* DatabaseManager.getPageLength();
        return read(headerName,fromID,fromID+ DatabaseManager.getPageLength(),selectors);
    }

    @Override
    public List<Row> read(String headerName,int fromID, int toID) throws SQLException, ClassNotFoundException {
        if (initReader(headerName, fromID, toID)) return new LinkedList<>();
        ResultSet resultSet = getResultSet(getSelect());
        return getRows(resultSet);
    }

    public boolean initReader(String headerName, int fromID, int toID) throws ClassNotFoundException, SQLException {
        if (notExist(headerName)) return true;
        this.fromID = fromID;
        this.toID = toID - 1;
        init(headerName);
        return false;
    }

    @Override
    public List<Row> read(String headerName, int fromID, int toID, Selector ...selectors) throws SQLException, ClassNotFoundException {
        if (notExist(headerName)) return new LinkedList<>();
        this.fromID = fromID;
        this.toID = toID-1;
        init(headerName);
        ResultSet resultSet = getResultSet(getSelect()+ getSelectors(selectors));
        return getRows(resultSet);
    }


    private ResultSet getResultSet(String query) throws SQLException {
        return getPreparedStatement(query).executeQuery();
    }

    private String getSelect() {
        return "select * from " + actualHeaderName + " where "
                + getActualHeader().getPrimaryKeyName()
                + " between " + fromID + " and " + toID;
    }

    private List<Row> getRows(ResultSet resultSet) throws SQLException {
        List<Row> rows = new LinkedList<>();
        while (resultSet.next()){
            rows.add(new SQLiteDataReader().readData(resultSet));
        }
        return rows;
    }

    @Override
    public int readCount(String headerName) throws SQLException, ClassNotFoundException {
        if (notExist(headerName)) return 0;
        init(headerName);
        return getCount(getResultSet(getSelectCount()));
    }

    private String getSelectCount() {
        return "select count(*) AS count from " + actualHeaderName + ";";
    }

    private int getCount(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("count");
    }
}
