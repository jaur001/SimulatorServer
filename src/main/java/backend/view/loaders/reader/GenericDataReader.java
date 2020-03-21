package backend.view.loaders.reader;


public interface GenericDataReader<T> {
    T readData(Object document);
}
