package backend.view.loaders.database.controller;

import backend.view.loaders.database.elements.Field;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TableCreator {
    void createTable(String tableName,Map<String, Field> parameters) throws SQLException, ClassNotFoundException;
    void dropTable(String tableName) throws SQLException, ClassNotFoundException;
}
