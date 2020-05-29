package backend.view.loaders.database.elements;

public interface Selector {
    String getInstruction();
    String getFieldName();
    Object getValue();
}
