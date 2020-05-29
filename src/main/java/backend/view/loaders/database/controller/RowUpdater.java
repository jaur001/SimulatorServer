package backend.view.loaders.database.controller;

import backend.view.loaders.database.elements.Selector;
import backend.view.loaders.database.elements.selectors.EqualSelector;

import java.sql.SQLException;

public interface RowUpdater {
    void updateRow(String headerName, EqualSelector[] equalSelectors, Selector... selectors) throws SQLException, ClassNotFoundException;
}
