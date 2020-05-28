package backend.view.loaders.database.builder;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.view.loaders.database.elements.Row;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Builder<T> {
    public List<T> buildList(List<Row> rows) {
        return rows.stream()
                .map(this::build)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public T build(Row row){
        return getItem(row.getParameters().toArray());
    }

    public  Row buildRow(T object){
        return new Row(getRow(object));
    }

    protected abstract List<Object> getRow(T object);

    public abstract String getHeader();

    protected abstract T getItem(Object[] parameters);
}
