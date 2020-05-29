package backend.view.loaders.database.elements.selectors;

import backend.view.loaders.database.elements.Selector;

public class LikeSelector implements Selector {

    private String fieldName;
    private String value;

    public LikeSelector(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public String getInstruction() {
        return " " + fieldName + " like '%" + value + "%'";
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
