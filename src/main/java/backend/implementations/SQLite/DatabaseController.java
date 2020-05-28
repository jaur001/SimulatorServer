package backend.implementations.SQLite;

import backend.view.loaders.database.DatabaseManager;
import backend.view.loaders.database.elements.Header;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DatabaseController {

    protected Connection connection;
    protected String actualHeaderName;
    protected List<Header> headerList = DatabaseManager.getHeaders();

    protected Header getActualHeader(String actualHeaderName) {
        return headerList.stream().filter(header -> header.getName().equals(actualHeaderName)).findFirst().orElse(null);
    }

    protected boolean notContains(String headerName){
        return headerList.stream().noneMatch(header -> header.getName().equals(headerName));
    }

    protected void init(String actualHeaderName) throws ClassNotFoundException, SQLException {
        this.actualHeaderName = actualHeaderName;
        this.connection = new SQLiteDatabaseConnector().connect();
    }

    protected boolean checkTable(String headerName) {
        if(notContains(headerName)){
            System.out.println("Error: Table does not exist");
            return true;
        }
        return false;
    }
}
