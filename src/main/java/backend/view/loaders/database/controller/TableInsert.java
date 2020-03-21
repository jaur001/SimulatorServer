package backend.view.loaders.database.controller;

import backend.view.loaders.database.elements.Row;

import java.sql.SQLException;
import java.util.List;

public interface TableInsert {
    void insert(String actualHeaderName, Row parameters) throws SQLException, ClassNotFoundException;
}
