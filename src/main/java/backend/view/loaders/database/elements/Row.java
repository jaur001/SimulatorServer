package backend.view.loaders.database.elements;

import java.util.List;

public class Row {
    private List<Object> parameters;

    public Row(List<Object> parameters) {
        this.parameters = parameters;
    }

    public List<Object> getParameters() {
        return parameters;
    }
}
