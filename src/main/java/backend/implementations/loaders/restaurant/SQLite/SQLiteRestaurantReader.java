package backend.implementations.loaders.restaurant.SQLite;

import backend.model.simulables.restaurant.Restaurant;
import backend.view.loaders.restaurant.RestaurantReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteRestaurantReader implements RestaurantReader {

    public static final String SQL_LOAD_RESTAURANT = "select * from Restaurant";
    public static final String SQLITE_CLASS = "org.sqlite.JDBC";
    private static Connection connection = null;
    private static String SQLiteUrl = "jdbc:sqlite:Simulator.db";
    private static int COLUMN_NUMBER = 7;

    private int count = 0;



    public static String getSQLiteUrl() {
        return SQLiteUrl;
    }

    public static void setSQLiteUrl(String SQLiteUrl) {
        SQLiteRestaurantReader.SQLiteUrl = SQLiteUrl;
    }

    @Override
    public List<Restaurant> read(int count){
        this.count = count;
        connect();
        return readRestaurants();
    }

    private static void connect(){
        try {
            if (connection == null){
                Class.forName(SQLITE_CLASS);
                connection = DriverManager.getConnection(SQLiteUrl);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Restaurant> readRestaurants() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOAD_RESTAURANT);
            List<Restaurant> restaurantList = getRestaurants(preparedStatement.executeQuery());
            preparedStatement.close();
            disconnect();
            return restaurantList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<Restaurant> getRestaurants(ResultSet resultSet) {
        try {
            return iterate(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private List<Restaurant> iterate(ResultSet resultSet) throws SQLException {
        List<Restaurant> restaurantList = new ArrayList<>();
        int pos = 1;
        while (resultSet.next() && pos++ <= count){
            restaurantList.add(new SQLiteRestaurantDataReader().readData(resultSet));
        }
        return restaurantList;
    }

    private void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
