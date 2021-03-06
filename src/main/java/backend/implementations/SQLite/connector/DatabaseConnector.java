package backend.implementations.SQLite.connector;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DatabaseConnector {
    protected static String url;
    protected static Connection connection;

    public static String getUrl() {
        return url;
    }

    public static void setUri(String url) {
        DatabaseConnector.url = url;
    }
    public abstract Connection connect() throws ClassNotFoundException, SQLException;
    public abstract void disconnect() throws SQLException;
}
