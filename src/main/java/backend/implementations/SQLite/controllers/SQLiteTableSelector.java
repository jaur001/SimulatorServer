package backend.implementations.SQLite.controllers;

import backend.implementations.SQLite.DatabaseController;
import backend.view.loaders.database.DatabaseManager;
import backend.view.loaders.database.controller.TableSelector;
import backend.view.loaders.database.elements.Row;
import backend.view.loaders.database.elements.Selector;
import backend.view.loaders.database.elements.selectors.OrderBySelector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SQLiteTableSelector extends DatabaseController implements TableSelector {
    private int fromID;
    private int toID;


    @Override
    public List<Row> read(String headerName, int page) throws SQLException, ClassNotFoundException {
        int fromID = getActualHeader(headerName).getInitialPrimaryKeyValue()+(page-1)* DatabaseManager.getPageLength();
        return read(headerName,fromID,fromID+ DatabaseManager.getPageLength());
    }

    @Override
    public List<Row> read(String headerName, int page, Selector...selectors) throws SQLException, ClassNotFoundException {
        int fromID = getActualHeader(headerName).getInitialPrimaryKeyValue()+(page-1)* DatabaseManager.getPageLength();
        return read(headerName,fromID,fromID+ DatabaseManager.getPageLength(),selectors);
    }

    @Override
    public List<Row> read(String headerName,int fromID, int toID) throws SQLException, ClassNotFoundException {
        if (checkTable(headerName)) return new LinkedList<>();
        this.fromID = fromID;
        this.toID = toID-1;
        init(headerName);
        ResultSet resultSet = getResultSet(getSelect());
        return getRows(resultSet);
    }

    @Override
    public List<Row> read(String headerName, int fromID, int toID, Selector ...selectors) throws SQLException, ClassNotFoundException {
        if (checkTable(headerName)) return new LinkedList<>();
        this.fromID = fromID;
        this.toID = toID-1;
        init(headerName);
        ResultSet resultSet = getResultSet(getSelect()+ getSelectors(selectors));
        return getRows(resultSet);
    }


    private ResultSet getResultSet(String query) throws SQLException {
        return connection.prepareStatement(query).executeQuery();
    }

    private String getSelect() {
        return "select * from " + actualHeaderName + " where "
                + getActualHeader(actualHeaderName).getPrimaryKeyName()
                + " between " + fromID + " and " + toID;
    }

    private String getSelectors(Selector ...selectors) {
        String result = getAndSelectors(selectors);
        return result + getNotAndSelectors(selectors);

    }

    private String getAndSelectors(Selector[] selectors) {
        return Arrays.stream(selectors)
                    .filter(selector -> !(selector instanceof OrderBySelector))
                    .map(this::addSelector)
                    .reduce("", String::concat);
    }

    private String getNotAndSelectors(Selector[] selectors) {
        return Arrays.stream(selectors)
                .filter(selector -> selector instanceof OrderBySelector)
                .map(Selector::getInstruction)
                .findFirst().orElse("");
    }

    private String addSelector(Selector selector) {
        return " and " + selector.getInstruction();
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
        if (checkTable(headerName)) return 0;
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
