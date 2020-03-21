package backend.view.loaders.database.elements;

import java.util.Map;

public class Header {
    private String name;
    private Map<String, Field> parameters;
    private String primaryKeyName;

    public Header(String name, Map<String, Field> parameters) {
        this.name = name;
        this.parameters = parameters;
        primaryKeyName = getPrimaryKey();
    }

    private String getPrimaryKey() {
        return parameters.keySet().stream()
                .filter(fieldName -> parameters.get(fieldName).getRestriction().equals(Restriction.PRIMARY_KEY))
                .findFirst()
                .orElseGet(this::searchPrimaryKey);
    }

    private String searchPrimaryKey() {
        return parameters.keySet().stream()
                .findFirst()
                .map(this::addPrimaryKey)
                .orElse("");
    }

    private String addPrimaryKey(String fieldName) {
        parameters.get(fieldName).setRestriction(Restriction.PRIMARY_KEY);
        return fieldName;
    }

    public String getName() {
        return name;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public Map<String, Field> getFields() {
        return parameters;
    }
}
