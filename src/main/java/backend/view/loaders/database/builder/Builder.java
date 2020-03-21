package backend.view.loaders.database.builder;
import backend.model.simulables.client.Client;
import backend.model.simulables.client.PersonalData;
import backend.view.loaders.database.elements.Row;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Builder<T> {
    public List<T> buildList(List<Row> rows) {
        return rows.stream()
                .map(this::build)
                .collect(Collectors.toList());
    }

    public T build(Row row){
        return getItem(row.getParameters().toArray());
    }

    public  Row buildRow(T object){
        return new Row(getRow(object));
    }

    protected abstract List<Object> getRow(T object);

    protected abstract T getItem(Object[] parameters);
}
