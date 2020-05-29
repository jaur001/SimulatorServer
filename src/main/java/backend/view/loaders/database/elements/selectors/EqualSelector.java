package backend.view.loaders.database.elements.selectors;

import backend.view.loaders.database.elements.Selector;

public class EqualSelector implements Selector {

    private String fieldName;
    private String value;

    public EqualSelector(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public String getInstruction() {
        return " " + fieldName + " = " + value;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
