package backend.implementations.SQLite.controllers;

import backend.implementations.SQLite.DatabaseController;
import backend.view.loaders.database.controller.TableCreator;
import backend.view.loaders.database.elements.Field;
import backend.view.loaders.database.elements.Header;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;


public class SQLiteTableCreator extends DatabaseController implements TableCreator {


    private Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public void createTable(Header header) throws SQLException, ClassNotFoundException {
        createTable(header.getName(),header.getFields());
    }

    @Override
    public void createTable(String headerName, Map<String, Field> parameters) throws SQLException, ClassNotFoundException {
        init(headerName);
        Statement statement = getStatement();
        statement.execute(createTable(parameters));
        statement.close();
    }

    private String createTable(Map<String, Field> parameters) {
        String result = getParameters(parameters);
        result = result.substring(0,result.length()-2);
        System.out.println("CREATE TABLE IF NOT EXISTS "+ actualHeaderName +" (\n" + result + ");");
        return "CREATE TABLE IF NOT EXISTS "+ actualHeaderName +" (\n" + result + ");";
    }

    private String getParameters(Map<String, Field> parameters) {
        return parameters.keySet().stream()
                .map(parameterName -> getParameter(parameters.get(parameterName),parameterName))
                .reduce(String::concat).orElse("");
    }


    private String getParameter(Field field, String parameterName){
        return " " + parameterName + " " + field.getDataType() + " " + field.getRestriction() + ",\n";
    }

    @Override
    public void dropTable(String headerName) throws SQLException, ClassNotFoundException {
        init(headerName);
        Statement statement = getStatement();
        statement.execute(dropTable());
        statement.close();
    }

    public void dropTable(Header header) throws SQLException, ClassNotFoundException {
        dropTable(header.getName());
    }

    private String dropTable() {
        return "drop table " + actualHeaderName + ";";
    }



}
