package backend.implementations.database.SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseConnector extends DatabaseConnector {

    public static final String SQLITE_CLASS = "org.sqlite.JDBC";

    static {
        url = "jdbc:sqlite:C:/Users/PROPIETARIO/Desktop/HPDS/SimulatorServer/out/artifacts/RestaurantSimulator_war_exploded/Simulator.db";
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
    }
}
