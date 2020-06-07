package backend.implementations.SQLite.controllers;

import backend.implementations.SQLite.DatabaseController;
import backend.view.loaders.database.controller.RowUpdater;
import backend.view.loaders.database.elements.Selector;
import backend.view.loaders.database.elements.selectors.EqualSelector;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.IntStream;

public class SQLiteRowUpdater extends DatabaseController implements RowUpdater {

    public void updateRow(String headerName, EqualSelector equalSelector, Selector... selectors) throws SQLException, ClassNotFoundException {
        updateRow(headerName,new EqualSelector[]{equalSelector},selectors);
    }

    @Override
    public void updateRow(String headerName, EqualSelector[] equalSelectors, Selector... selectors) throws SQLException, ClassNotFoundException {
        if (notExist(headerName)) return;
        init(headerName);
        PreparedStatement preparedStatement = getPreparedStatement(getQuery(equalSelectors,selectors));
        prepareUpdate(equalSelectors, preparedStatement);
        preparedStatement.executeUpdate();
    }

    public void prepareUpdate(EqualSelector[] equalSelectors, PreparedStatement preparedStatement) {
        IntStream.range(0,equalSelectors.length).boxed()
                .forEach(pos -> {
                    try {
                        insertValue(equalSelectors[pos].getValue(),preparedStatement,pos);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    private String getQuery(EqualSelector[] equalSelectors, Selector... selectors) {
        String query = "update " + actualHeaderName + " set";
        query += getFieldToChange(equalSelectors);
        return checkQuery(query + " where"+ getAndSelectors(selectors));
    }
}
