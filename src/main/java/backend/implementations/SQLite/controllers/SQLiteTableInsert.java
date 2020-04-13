package backend.implementations.SQLite.controllers;

import backend.implementations.SQLite.DatabaseController;
import backend.view.loaders.database.controller.TableInsert;
import backend.view.loaders.database.elements.Header;
import backend.view.loaders.database.elements.Row;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SQLiteTableInsert extends DatabaseController implements TableInsert {


    @Override
    public void insert(String headerName, Row parameters) throws SQLException, ClassNotFoundException {
        if (checkTable(headerName)) return;
        init(headerName);
        PreparedStatement preparedStatement = prepareInsert();
        insertValues(parameters.getParameters(),preparedStatement);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private PreparedStatement prepareInsert() throws SQLException {
        return connection.prepareStatement(prepareHeader());
    }

    private String prepareHeader(){
        final String[] firstSection = {"insert into " + actualHeaderName +" ("};
        final String[] secondSection = {") values ("};
        concatParameters(firstSection, secondSection);
        firstSection[0] = firstSection[0].substring(0, firstSection[0].length()-2);
        secondSection[0] = secondSection[0].substring(0, secondSection[0].length()-2);
        return firstSection[0] +secondSection[0] +")";
    }


    private void concatParameters(String[] firstSection,String[] secondSection){
        Header header = getActualHeader(actualHeaderName);
        header.getFields().keySet().forEach(parameter -> {
            firstSection[0] += concatParameter(parameter);
            secondSection[0] += concatParameter("?");
        });
    }


    private String concatParameter(String parameter) {
        return parameter + ", ";
    }


    private void insertValues(List<Object> parameters,PreparedStatement preparedStatement){
        AtomicInteger position = new AtomicInteger();
        getActualHeader(actualHeaderName).getFields().forEach((fieldName, field) -> {
            try {
                insertRow(parameters.get(position.get()), preparedStatement, position.get());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            position.getAndIncrement();
        });
    }

    private void insertRow(Object parameter, PreparedStatement preparedStatement, int position) throws SQLException {
        preparedStatement.setObject(position+1,parameter);
    }
}
