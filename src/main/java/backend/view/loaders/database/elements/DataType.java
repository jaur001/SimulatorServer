package backend.view.loaders.database.elements;

public enum DataType {
    integer,
    real,
    text;

    @Override
    public String toString() {
        return this.name();
    }

}
