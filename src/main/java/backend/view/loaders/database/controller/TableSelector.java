package backend.view.loaders.database.controller;


import backend.view.loaders.database.elements.Row;

import java.sql.SQLException;
import java.util.List;

public interface TableSelector {
    List<Row> read(String headerName, int fromID, int toID) throws SQLException, ClassNotFoundException;
}
