package backend.view.loaders.database.elements;

public class Field {
    private Restriction restriction;
    private DataType dataType;

    public Field(Restriction restriction, DataType dataType) {
        this.restriction = restriction;
        this.dataType = dataType;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }

    public DataType getDataType() {
        return dataType;
    }
}
