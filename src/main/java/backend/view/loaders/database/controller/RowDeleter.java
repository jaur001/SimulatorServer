package backend.view.loaders.database.controller;

import java.sql.SQLException;

public interface RowDeleter {
    void deleteAll(String headerName) throws SQLException, ClassNotFoundException;
}
