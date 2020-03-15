package backend.implementations.loaders.restaurant.SQLite;

import backend.model.simulables.restaurant.Restaurant;
import backend.view.loaders.restaurant.DatabaseRestaurantWriter;

import java.sql.*;
import java.util.List;

public class SQLRestaurantWriter implements DatabaseRestaurantWriter {

    private static Connection connection = null;
    private static String SQLiteUrl = "jdbc:sqlite:Simulator.db";

    public static String getSQLiteUrl() {
        return SQLiteUrl;
    }

    public static void setSQLiteUrl(String SQLiteUrl) {
        SQLRestaurantWriter.SQLiteUrl = SQLiteUrl;
    }

    @Override
    public void write(List<Restaurant> restaurantList) {
        try {
            connect();
            createTableIfNotExit();
            writeRestaurants(restaurantList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    private static void connect(){
        try {
            if (connection == null){
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(SQLiteUrl);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExit() throws SQLException {
        Statement statement = getStatement();
        statement.execute(getTable());
        statement.close();
    }

    private Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    private String getTable() {
        return "CREATE TABLE IF NOT EXISTS Restaurant (\n"
                + " NIF integer PRIMARY KEY,\n"
                + " name text NOT NULL UNIQUE,\n"
                + " telephoneNumber text NOT NULL,\n"
                + " street text NOT NULL,\n"
                + " minPrice integer NOT NULL,\n"
                + " maxPrice integer NOT NULL,\n"
                + " numberTables integer NOT NULL);";
    }

    private void writeRestaurants(List<Restaurant> restaurantList) {
        restaurantList.forEach(this::addRestaurant);
    }

    private void addRestaurant(Restaurant restaurant) {
        String insert = " insert into Restaurant (NIF, name, street, telephoneNumber, minPrice, maxPrice, numberTables)"
                + " values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = prepareInsert(restaurant, insert);
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Restaurant added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement prepareInsert(Restaurant restaurant, String insert) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        setData(restaurant, preparedStatement);
        return preparedStatement;
    }

    private void setData(Restaurant restaurant, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt (1, restaurant.getNIF());
        preparedStatement.setString(2, restaurant.getName());
        preparedStatement.setString(3, restaurant.getStreet());
        preparedStatement.setString(4, restaurant.getTelephoneNumber());
        preparedStatement.setInt(5, restaurant.getMinPricePlate());
        preparedStatement.setInt(6, restaurant.getMaxPricePlate());
        preparedStatement.setInt(7, restaurant.getTables());
    }

    private void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        SQLRestaurantWriter.connection = connection;
    }
}
