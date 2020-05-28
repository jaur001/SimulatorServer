package backend.view.loaders.database.controller;


import backend.view.loaders.database.elements.OrderBy;
import backend.view.loaders.database.elements.Row;
import backend.view.loaders.database.elements.Selector;

import java.sql.SQLException;
import java.util.List;

public interface TableSelector {
    List<Row> read(String headerName, int page) throws SQLException, ClassNotFoundException;
    List<Row> read(String headerName, int page, Selector ...selectors) throws SQLException, ClassNotFoundException;
    List<Row> read(String headerName, int fromID, int toID) throws SQLException, ClassNotFoundException;
    List<Row> read(String headerName, int fromID, int toID, Selector ...selectors) throws SQLException, ClassNotFoundException;
    int readCount(String headerName) throws SQLException, ClassNotFoundException;
}
