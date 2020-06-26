package backend.implementations.SQLite.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseConnector extends DatabaseConnector {

    public static final String SQLITE_CLASS = "org.sqlite.JDBC";

    static {
        url = "";
    }

    @Override
    public Connection connect() throws ClassNotFoundException, SQLException {
        if (connection == null){
            Class.forName(SQLITE_CLASS);
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }

    @Override
    public void disconnect() throws SQLException {
        connection.close();
        connection = null;
    }
}
