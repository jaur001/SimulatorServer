package backend.implementations.SQLite;

import backend.implementations.SQLite.connector.SQLiteDatabaseConnector;
import backend.view.loaders.database.DatabaseManager;
import backend.view.loaders.database.elements.Header;
import backend.view.loaders.database.elements.Selector;
import backend.view.loaders.database.elements.selectors.EqualSelector;
import backend.view.loaders.database.elements.selectors.OrderBySelector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public abstract class DatabaseController {

    protected Connection connection;
    protected String actualHeaderName;
    protected List<Header> headerList = DatabaseManager.getHeaders();

    protected Header getActualHeader() {
        return headerList.stream().filter(header -> header.getName().equals(actualHeaderName)).findFirst().orElse(null);
    }

    protected boolean notContains(String headerName){
        return headerList.stream().noneMatch(header -> header.getName().equals(headerName));
    }

    protected void init(String actualHeaderName) throws ClassNotFoundException, SQLException {
        this.actualHeaderName = actualHeaderName;
        this.connection = new SQLiteDatabaseConnector().connect();
    }

    protected boolean notExist(String headerName) {
        if(notContains(headerName)){
            System.out.println("Error: Table does not exist");
            return true;
        }
        return false;
    }

    protected Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    protected PreparedStatement getPreparedStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    protected String getSelectors(Selector...selectors) {
        String result = getAndSelectors(selectors);
        return result + getOrderBySelector(selectors);

    }

    protected String getAndSelectors(Selector[] selectors) {
        return Arrays.stream(selectors)
                .filter(selector -> !(selector instanceof OrderBySelector))
                .map(Selector::getInstruction)
                .reduce("",(s1, s2) -> s1+" and"+s2);
    }

    protected String getOrderBySelector(Selector[] selectors) {
        return Arrays.stream(selectors)
                .filter(selector -> selector instanceof OrderBySelector)
                .map(Selector::getInstruction)
                .findFirst().orElse("");
    }

    protected String getFieldToChange(EqualSelector[] equalSelectors) {
        return Arrays.stream(equalSelectors)
                .map(selector -> selector.getFieldName() +" = ?")
                .reduce(" ",(s1, s2) -> s1+" , "+s2)
                .replaceAll(" , "," ");
    }

    protected String checkQuery(String query){
        return query.replaceAll("where and", "where");
    }

    protected void insertValue(Object parameter, PreparedStatement preparedStatement, int position) throws SQLException {
        preparedStatement.setObject(position+1,parameter);
    }

}
