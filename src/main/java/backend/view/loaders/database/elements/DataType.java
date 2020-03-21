package backend.view.loaders.database.elements;

public enum DataType {
    integer,
    text;

    @Override
    public String toString() {
        return this.name();
    }

}
