package backend.implementations.SQLite;

import backend.view.loaders.database.elements.Row;
import backend.view.loaders.reader.GenericDataReader;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SQLiteDataReader implements GenericDataReader<Row> {
    @Override
    public Row readData(Object document) {
        ResultSet resultSet = (ResultSet) document;
        int length = 0;
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            length = resultSetMetaData.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Row(getRow(resultSet,length));
    }

    private List<Object> getRow(ResultSet resultSet, int length) {
        return IntStream.range(0,length).boxed()
                .map(position -> {
            try {
                return resultSet.getObject(position+1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new Object();
        }).collect(Collectors.toCollection(LinkedList::new));
    }
}
