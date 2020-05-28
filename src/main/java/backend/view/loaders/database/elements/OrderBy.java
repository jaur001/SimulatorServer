package backend.view.loaders.database.elements;

public enum  OrderBy {
    ASC,
    DESC;

    @Override
    public String toString() {
        return this.name();
    }
}
